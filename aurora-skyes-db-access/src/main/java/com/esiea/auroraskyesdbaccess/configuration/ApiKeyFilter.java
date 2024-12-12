package com.esiea.auroraskyesdbaccess.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

	@Value("${api.key}")
	private String configuredApiKey;

	@Override
	protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return requestURI.startsWith("/actuator/prometheus");
	}

	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		String apiKey = request.getHeader("x-api-key");

		if (apiKey == null || !apiKey.trim().equals(configuredApiKey.trim())) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write("Invalid API Key");
			return;
		}

		Authentication authentication = new UsernamePasswordAuthenticationToken("apiKeyUser", null, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		filterChain.doFilter(request, response);
	}

}

