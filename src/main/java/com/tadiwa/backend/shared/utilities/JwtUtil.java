package com.tadiwa.backend.shared.utilities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tadiwa.backend.features.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.signingKey}")
    private String signingKeyStr;

    @Value("${jwt.encryptionKey}")
    private String encryptionKeyStr;

    private SecretKey strToKey(String str) {
        byte[] keyBytes = str.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey signingKey() {
        return strToKey(signingKeyStr);
    }

    private SecretKey encryptionKey() {
        return strToKey(encryptionKeyStr);
    }

    public String issueToken(Map<String, Object> claims, String sub) {

        Date expiryDate  = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        return Jwts.builder()
            .subject(sub)
            .issuedAt(new Date(System.currentTimeMillis()))
            .claims(claims)
            .signWith(encryptionKey())
            .signWith(signingKey())
            .expiration(expiryDate)
            .issuer("ZMS")
            .compact();
    }

    public String issueToken(User user) {
        String sub = user.getEmail();
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getId());

        return issueToken(claims, sub);
    }

    public Claims verifyToken(String token) {
        Claims claims = (Claims) Jwts.parser()
            .decryptWith(encryptionKey())
            .verifyWith(signingKey())
            .build()
            .parse(token)
            .getPayload();

        return claims;
    }

    

}
