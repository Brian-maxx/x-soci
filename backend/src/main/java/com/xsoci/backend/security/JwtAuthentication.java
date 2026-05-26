package com.xsoci.backend.security;

import org.springframework.stereotype.Service;
import com.xsoci.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtAuthentication {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(User user) {
        return Jwts.builder()
            .subject(user.getEmail())
            .claim("role", user.getRole().getRoleName())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis() + accessTokenExpiration
                )
            )
            .signWith(getSignInKey())
            .compact();
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
            .subject(user.getEmail())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis() + refreshTokenExpiration
                )
            )
            .signWith(getSignInKey())
            .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration)
            .before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) 
            && !isTokenExpired(token);
    }
}
