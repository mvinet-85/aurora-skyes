package com.esiea.auroraskyesdbaccess.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.SecretKey;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	/** Clé JWT */
	private static final SecretKey SECRET_KEY = Keys
			.hmacShaKeyFor("aled_aled_aled_aled_aled_aled_aled_aled_aled_aled".getBytes());

	@Override
	protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return requestURI.startsWith("/actuator/prometheus") || requestURI.startsWith("/utilisateurs") || requestURI.startsWith("/authentification");
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		LOGGER.info("Lancement du JWT filter pour la requête : {}", request.getRequestURI());

		final String authHeader = request.getHeader("Authorization");
		LOGGER.debug("Authorization Header: {}", authHeader);

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			LOGGER.warn("Aucun token Bearer trouvé dans l'en-tête Authorization.");
			filterChain.doFilter(request, response);
			return;
		}

		final String token = authHeader.substring(7);
		try {
			String email = Jwts.parser()
					.verifyWith(SECRET_KEY)
					.build()
					.parseSignedClaims(token)
					.getPayload()
					.getSubject();

			LOGGER.info("JWT valide pour l'utilisateur : {}", email);

			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				User user = new User(email, "", new ArrayList<>());
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);

				LOGGER.info("Authentification ajoutée au contexte de sécurité pour : {}", email);
			}
		} catch (io.jsonwebtoken.security.SecurityException e) {
			LOGGER.error("Signature JWT invalide : {}", e.getMessage());
		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			LOGGER.error("JWT expiré : {}", e.getMessage());
		} catch (io.jsonwebtoken.MalformedJwtException e) {
			LOGGER.error("JWT malformé : {}", e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Erreur inconnue lors du traitement du JWT : {}", e.getMessage());
		}

		LOGGER.info("Poursuite de la chaîne de filtres pour la requête : {}", request.getRequestURI());
		filterChain.doFilter(request, response);
	}
}
