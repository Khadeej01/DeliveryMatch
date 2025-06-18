package org.deliverymatch.backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.deliverymatch.backend.model.utilisateur.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private String secret = "yourSecretKey"; // Remplacez par une clé sécurisée
    private int expiration = 86400000; // 24 heures

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}