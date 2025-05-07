package com.lauder.app.ecommapp.util;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import javax.crypto.SecretKey;
import org.springframework.http.HttpHeaders;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.nio.charset.StandardCharsets;

@Component
public class JwtService {
    static final long EXPIRATION_TIME = 300_000; // 5 minutes
    static final String PREFIX = "Bearer ";
    static final String SECRET = "your-256-bit-secret-your-256-bit-secret";
    static final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String getToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith(PREFIX + " ")) {
            token = token.substring((PREFIX + " ").length());
            try {
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();
                return claims.getSubject();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
