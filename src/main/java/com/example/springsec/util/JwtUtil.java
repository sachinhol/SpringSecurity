package com.example.springsec.util;/*
 * Author: Your Name
 * Date: 07-Nov-24
 * Time: 5:30 PM
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private String secretKey = null; // Ideally, this should be stored securely

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuer("DEMO")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1hour expiration
                .signWith(SignatureAlgorithm.HS256, getSecretKey())
                .compact();
    }

    public SecretKey getSecretKey() {
        String key =  "3850593b993da31a50f7c06fb4a8c595b61cefdb09426fc8035940669887bd128d0d66a00e4b3f994941747e49e25c0cd293626e69eec959a27ec182eee97635808ec6ca5d41e85aa6148eef2bc6ef479d74c5b6acea12e653083dd7aced4651853048b51c33cf749aa57f5725620824369098521f5173815473d0d8cbcb50e46399491395812261ac8b054c1ff4f8d2b34af1cbe94fdc19fb00a0e2d6c65c9a6a5c64aac9662377a2632286bfff70ea3eea4c82a1f2b4a3250b64f89863c3d122c238117cc9cf609acd1a1ea969b3cbd77449a8da5a06121e6676bf882236237a22391d40256c53af0bdeea8b4ab8d94a562b2afdeb9a0d85370b869b52fa75";
        return Keys.hmacShaKeyFor(key.getBytes());

    }

    public String extractUserName(String jwtToken) {
        Claims payload = getClaims(jwtToken);
        String userName = payload.getSubject();
        return userName;
    }

    private Claims getClaims(String jwtToken) {
        Claims payload = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return payload;
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String userName =  extractUserName(jwt);
        Claims claims = getClaims(jwt);
        boolean isExpired =  claims.getExpiration().before(new Date());
        if(isExpired){
            return false;
        }
        return userName.equals(userDetails.getUsername());
    }
}
