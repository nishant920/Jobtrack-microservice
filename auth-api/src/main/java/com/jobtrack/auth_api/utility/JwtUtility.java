package com.jobtrack.auth_api.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtility {
    //JWT - JSON WEB TOKEN, CONTAIN HEADER.PAYLOAD.SIGNATURE
    /*HEADER - JWT meta data*/
    @Value("${jwt.secret.password}")
    String secretPass;
    @Value("${jwt.secret.password}")
    long expirationTime;

    public String generateToken(String email, String role){
        return Jwts.builder().
                setSubject(email).
                claim("role", role).setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + expirationTime)).
                signWith(Keys.hmacShaKeyFor(secretPass.getBytes()), SignatureAlgorithm.HS256).
                compact();
    }

}
