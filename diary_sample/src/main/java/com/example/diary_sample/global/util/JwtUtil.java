package com.example.diary_sample.global.util;

import com.example.diary_sample.feature.member.dto.SignUpRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;

@Component @Slf4j @RequiredArgsConstructor
public class JwtUtil {
    private final Key key;
    @Value("${jwt.secret}")
    private final String secret;
    private final long expirationTime = 24 * 60 * 60 * 365;


    public String createToken(SignUpRequest request) {
        return createToken(request, expirationTime);
    }

    private String createToken(SignUpRequest request, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("email", request.getEmail());
        claims.put("name", request.getName());
        claims.put("role", request.getMemberRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidate = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims
                .setIssuedAt(Date.from(now.toInstant())))
                .setExpiration(Date.from(tokenValidate.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseClaims(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken).getBody();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // getUserId?
    public String getUserEmail(String email) {
        return parseClaims(email).get("email", String.class);
    }

}
