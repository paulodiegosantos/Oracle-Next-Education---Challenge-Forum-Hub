package br.com.challenge.forumhub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration; // em minutos

    // Gera o token JWT
    public String gerarToken(String username) {
        Instant agora = Instant.now();
        return JWT.create()
                .withSubject(username) // define o usuário no token
                .withIssuedAt(Date.from(agora)) // data de emissão
                .withExpiresAt(Date.from(agora.plus(expiration, ChronoUnit.MINUTES))) // expiração
                .sign(Algorithm.HMAC256(secret)); // assina com HMAC256
    }

    // Valida o token JWT
    public boolean isTokenValido(String token) {
        try {
            JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token); // lança exceção se inválido
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // Extrai o username do token JWT
    public String getUsername(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token)
                .getSubject();
    }

    // getters e setters para secret e expiration
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}