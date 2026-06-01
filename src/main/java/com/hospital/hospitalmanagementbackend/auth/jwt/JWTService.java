package com.hospital.hospitalmanagementbackend.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {

//    we use this secret key from app.props
    @Value("${jwt.secret-key}")
    private String secretKey;

//    generaate token boy!!!
    public String generateToken(String email) {

        return Jwts
                .builder().subject(email).issuedAt(new Date()).expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(
                    Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)),
                    Jwts.SIG.HS256
                )
                .compact();
    }

//    get username or email from token
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))
                )
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

//    check if token is valid or not
//    dhonga na yajamani na?
    public boolean isTokenValid(String token, String email) {

        final String username = extractUsername(token);
        return username.equals(email);
    }

}
