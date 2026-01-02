package com.placement.readiness.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // ⚠️ must be at least 256 bits
    private static final Key key =
            Keys.hmacShaKeyFor("my-super-secret-key-my-super-secret-key".getBytes());

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    // 1️⃣ Generate token
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2️⃣ Extract email (THIS IS WHAT YOU WERE MISSING)
    public static String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 3️⃣ Validate token
    public static boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Internal helper
    private static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
