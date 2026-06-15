package com.contasys.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Obtener llave de firma (SecretKey es preferible a Key en 0.12.x)
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generar Token
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey()) // El algoritmo se detecta automáticamente por el tamaño de la llave
                .compact();
    }

    // Extraer Username
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Validar Token
    public boolean isTokenValid(String token, String email) {
        final String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    // Validar Expiración
    private boolean isTokenExpired(String token) {
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extraer Claims (Aquí estaba el error de verifyWith)
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) // Requiere JJWT 0.12.0+
                .build()
                .parseSignedClaims(token)
                .getPayload(); // getBody() ahora es getPayload()
    }
}