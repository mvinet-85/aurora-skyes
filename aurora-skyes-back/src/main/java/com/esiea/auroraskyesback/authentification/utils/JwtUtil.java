package com.esiea.auroraskyesback.authentification.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private static final Key SECRET_KEY = Keys.hmacShaKeyFor("aled_aled_aled_aled_aled_aled_aled_aled_aled_aled".getBytes());

	public String generateToken(String email) {
		return Jwts.builder()
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiration après 10 heures
				.signWith(SECRET_KEY)
				.compact();
	}

}
