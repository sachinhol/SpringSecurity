package com.hossync.security.auth.service.impl;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import com.hossync.security.auth.config.PropertyConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Autowired
    private PropertyConfig propertyConfig;

    public String generateToken(String email){
        Key signingKey = getSigningKey();

        logger.info("signingKey while generating token : {}",signingKey);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String email){
    return(email.equals(extractUserEmail(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T>T extractClaim(String token, Function<Claims, T> claimResolver) {
        Key signingKey = getSigningKey();

        logger.info("signingKey while verifying token : {}",signingKey);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJwt(token)
                .getBody();

        return claimResolver.apply(claims);
    }


    // This method generates a signing key for JWT authentication
    private Key getSigningKey() {
        // Step 1: Retrieve the Base64-encoded secret key from application properties
        byte[] decode = Decoders.BASE64.decode(propertyConfig.getSecretKey());

        // Step 2: Convert the decoded byte array into an HMAC-SHA key
        return Keys.hmacShaKeyFor(decode);
    }
}
