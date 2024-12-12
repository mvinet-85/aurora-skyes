package com.esiea.auroraskyesback.configuration;

import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
	private static final String API_BASE_URL = "http://localhost:8081";

	private final RestTemplate restTemplate;

	public SecurityConfig(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return email -> {
			LOGGER.info("Tentative de chargement de l'utilisateur avec l'email : {}", email);
			try {
				// Effectuer une requête HTTP vers l'API externe pour récupérer l'utilisateur
				String url = API_BASE_URL + "/utilisateurs/email/" + email;
				UtilisateurBDDTO utilisateur = restTemplate.getForObject(url, UtilisateurBDDTO.class);

				if (utilisateur == null) {
					throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
				}

				LOGGER.info("Utilisateur trouvé : {}", utilisateur.getEmail());
				return new org.springframework.security.core.userdetails.User(
						utilisateur.getEmail(),
						utilisateur.getMotDePasse(),
						new ArrayList<>()); // Vous pouvez ajouter des rôles ici si nécessaire
			} catch (Exception e) {
				LOGGER.error("Erreur lors de la récupération de l'utilisateur avec l'email : {}", email, e);
				throw new UsernameNotFoundException("Utilisateur non trouvé : " + email, e);
			}
		};
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
			throws Exception {
		LOGGER.info("Configuration de la chaîne de filtres de sécurité...");
		http.csrf(AbstractHttpConfigurer::disable)
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(auth -> {
					LOGGER.info("Définition des règles d'autorisation...");
					auth.requestMatchers("/actuator/prometheus").permitAll();
					auth.requestMatchers("/authentification/**").permitAll();
					auth.requestMatchers("/utilisateurs/**").permitAll();
					auth.anyRequest().authenticated();
				})
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		LOGGER.info("Chaîne de filtres de sécurité configurée avec succès.");
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Création du bean BCryptPasswordEncoder...");
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
														 PasswordEncoder passwordEncoder) {
		LOGGER.info("Configuration du fournisseur d'authentification...");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		LOGGER.info("Fournisseur d'authentification configuré.");
		return provider;
	}

	@Bean
	public AuthenticationConfiguration authenticationConfiguration() {
		LOGGER.info("Création du bean AuthenticationConfiguration...");
		return new AuthenticationConfiguration();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		LOGGER.info("Création du bean AuthenticationManager...");
		return authenticationConfiguration.getAuthenticationManager();
	}
}
