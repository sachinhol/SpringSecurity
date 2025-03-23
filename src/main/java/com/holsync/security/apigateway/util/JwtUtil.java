package com.holsync.security.apigateway.util;/*
 * Author: Sachin
 * Date: 23-Mar-25
 * Description:
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);


    private final String secretKey =  "bW9uZXlrZXlzLXJ1bGUtam90YS1zZWNyZXQtMTIzNDU2";


    public boolean validateToken(String token) {
        try {
            String email = extractUserEmail(token);
            return email != null && !isTokenExpired(token);
        } catch (Exception e) {
            logger.error("Invalid JWT Token: {}", e.getMessage());
            return false;
        }
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
                .parseClaimsJws(token)
                .getBody();

        return claimResolver.apply(claims);
    }


    // This method generates a signing key for JWT authentication
    private Key getSigningKey() {
        // Step 1: Retrieve the Base64-encoded secret key from application properties
        byte[] decode = Decoders.BASE64.decode(secretKey);

        // Step 2: Convert the decoded byte array into an HMAC-SHA key
        return Keys.hmacShaKeyFor(decode);
    }
}
