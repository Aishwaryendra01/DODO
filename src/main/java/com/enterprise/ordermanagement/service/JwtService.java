package com.enterprise.ordermanagement.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.expiration-ms}")
    private long jwtExpirationMs;

    @Value("${jwt.secret}")
    private String secret;

    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetails userdetails) {

        Date now = new Date();

        Date expiryDate = new Date(
                now.getTime() + jwtExpirationMs
        );

        return Jwts.builder()
                .subject(userdetails.getUsername())
                .claim("role", userdetails.getAuthorities())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSignInKey())
                .compact();
    }
    
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    
    private boolean isTokenExpired(String token) {

        return extractExpiration(token).before(new Date());
    }
    
    private Date extractExpiration(String token) {

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}