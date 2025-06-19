package org.deliverymatch.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.deliverymatch.backend.model.utilisateur.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    private String secret = "your-secret-key-must-be-at-least-256-bits-long-for-security"; // Remplacez par une clé sécurisée
    private int expiration = 86400000; // 24 heures
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
}