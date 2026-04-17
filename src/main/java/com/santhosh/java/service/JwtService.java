package com.santhosh.java.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Slf4j
@Service
public class JwtService {


    public String genearateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSecKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSecKey() {
        byte[] keybytes = Decoders.BASE64.decode("uT6a9JY3Dyq1fL9+J7y8Vq2Kz8UtW1209Vg7NwBx3mc=w");
        return Keys.hmacShaKeyFor(keybytes);
    }

    public String extractUsername(String token)
    {
     return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        log.info("username is " + username);
        log.info("Is token expired? {}", isTokenExpired(token));
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSecKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
