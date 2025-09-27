package com.template.template.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final String SECRET = "replace_with_a_very_long_random_secret_key_1234567890";
  private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET.getBytes());
  }

  public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
