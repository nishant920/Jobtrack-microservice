package com.jobtrack.job_api.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtility {

    /*
     * Build a JWT parser and give it the same secret key that auth-api used
     * while creating/signing the token.
     *
     * This secret key is what allows job-api to verify that the JWT is trusted.
     */
    @Value("${jwt.secret.password}")
    private String secretPass;

    public String verifyJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                /*
                 * parseClaimsJws(token) does the main verification:
                 * - checks token format
                 * - checks token signature
                 * - checks if token was modified
                 * - checks token expiration
                 *
                 * If verification fails, this line throws an exception.
                 */
                .build()
                .parseClaimsJws(token)
                .getBody();

        /*
         * Auth-api stored the user's email inside token subject using:
         * setSubject(email)
         *
         * So here, claims.getSubject() returns that email.
         */
        return claims.getSubject();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretPass.getBytes());
    }
}
