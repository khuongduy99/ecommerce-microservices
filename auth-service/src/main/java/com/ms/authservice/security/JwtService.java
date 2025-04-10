package com.ms.authservice.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ms.authservice.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(User user) {
		return Jwts.builder().setSubject(user.getUsername()).claim("role", user.getRole().name()).setIssuedAt(new Date())
				.setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody()
				.getSubject();
	}
}
