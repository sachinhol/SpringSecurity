package com.holsync.security.SpringSecDemo.securities;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import com.holsync.security.SpringSecDemo.config.PropertyConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);


    @Autowired
    private PropertyConfig propertyConfig;

    //Generate Token
    public String generateToken(String email){
        Key signingKey = getSigningKey();
        logger.info("🔑 Signing Key at Token Generation: {}", signingKey); // Log it

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  //1hr Validity
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, String email){
        return (email.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T>T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Key signingKey = getSigningKey();
        logger.info("🔑 Signing Key at Token Validation: {}", signingKey); // Log key at validation

        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claimsResolver.apply(claims);
    }

    // Extract expiration date from token and Check if token is expired
    private boolean isTokenExpired(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }


    // ✅ Secure Signing Key Generator
    private Key getSigningKey() {

        logger.info("key : {}", propertyConfig.getSecretKey());

        byte[] keyBytes = Decoders.BASE64.decode(propertyConfig.getSecretKey());

        Key secretKey = Keys.hmacShaKeyFor(keyBytes);
        return secretKey;
    }

}
