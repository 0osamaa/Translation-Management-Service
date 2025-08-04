package com.osama.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private Key SECRET_KEY;
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    @PostConstruct
    public void init() {
        // Generate a secure key for HS512
        this.SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage(), e);
        }
    }

    public String getUsernameFromToken(String token) {
        return validateToken(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        try {
            return validateToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true; // Treat invalid tokens as expired
        }
    }
}