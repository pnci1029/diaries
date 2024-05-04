package com.example.diary_sample.global.util;

import com.example.diary_sample.feature.member.dto.MemberInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Base64;

@Component @Slf4j
public class JwtUtil {

    private Key key;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationTime}")
    private long expirationTime;

    @PostConstruct
    public void init() {
        byte[] decodedSecret = Base64.getDecoder().decode(secret);
        this.key = new SecretKeySpec(decodedSecret, SignatureAlgorithm.HS256.getJcaName());
    }

    public String createToken(MemberInfo request) {
        return createToken(request, expirationTime);
    }

    private String createToken(MemberInfo request, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("id", request.getId());
        claims.put("email", request.getEmail());
        claims.put("name", request.getName());
        claims.put("role", request.getMemberRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidate = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
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
    public String getUserId(String token) {
        return parseClaims(token).get("id", String.class);
    }
    public String getUserEmail(String token) {
        return parseClaims(token).get("email", String.class);
    }

}
