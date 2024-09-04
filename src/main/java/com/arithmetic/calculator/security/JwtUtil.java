package com.arithmetic.calculator.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Gera uma chave secreta segura para HS256
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Gera um token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de validade
                .signWith(key)
                .compact();
    }

    // Extrai as claims do token JWT
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)  // Usa a chave gerada para verificação
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrai o nome de usuário das claims do token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Verifica se o token está expirado
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token comparando o nome de usuário e verificando a expiração
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
