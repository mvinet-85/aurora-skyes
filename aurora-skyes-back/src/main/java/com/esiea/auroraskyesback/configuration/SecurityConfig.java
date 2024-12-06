package com.esiea.auroraskyesback.configuration;

import com.esiea.auroraskyesback.utilisateur.dao.UtilisateurDAO;
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

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public UserDetailsService userDetailsService(UtilisateurDAO utilisateurDAO) {
        return email -> {
            LOGGER.info("Tentative de chargement de l'utilisateur avec l'email : {}", email);
            return utilisateurDAO.findByEmail(email)
                    .map(user -> {
                        LOGGER.info("Utilisateur trouvé : {}", user.getEmail());
                        return new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getMotDePasse(),
                                new ArrayList<>()
                        );
                    })
                    .orElseThrow(() -> {
                        LOGGER.error("Aucun utilisateur trouvé avec l'email : {}", email);
                        return new UsernameNotFoundException("Utilisateur non trouvé : " + email);
                    });
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        LOGGER.info("Configuration de la chaîne de filtres de sécurité...");
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    LOGGER.info("Définition des règles d'autorisation...");
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
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        LOGGER.info("Création du bean AuthenticationManager...");
        return authenticationConfiguration.getAuthenticationManager();
    }
}
