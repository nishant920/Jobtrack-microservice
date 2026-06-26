package com.jobtrack.job_api.filter;

import com.jobtrack.job_api.utility.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;

    public AuthFilter(JwtUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }

    /*
     * This method is used by job-api to verify the JWT sent by the frontend.
     *
     * Important flow:
     * 1. User logs in through auth-api.
     * 2. Auth-api checks the user's email and password from auth database.
     * 3. If login is successful, auth-api creates a JWT.
     * 4. That JWT contains the user's email as the "subject".
     * 5. Frontend sends this JWT to job-api in the Authorization header.
     *    Example: Authorization: Bearer <token>
     * 6. Job-api does not check the user table because user data belongs to auth-api.
     * 7. Instead, job-api verifies the JWT signature using the same secret key.
     * 8. If the signature is valid, it means the token was really created by auth-api
     *    and was not changed by anyone.
     * 9. After verification, job-api extracts the email from the token subject.
     * 10. Job-api uses that email as the owner of the job records.
     *
     * parseClaimsJws(token) is the line that verifies the token signature.
     * If the token is invalid, expired, or modified, it throws an exception.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authHeader.substring(7);
            String email = jwtUtility.verifyJwt(token);

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                Collections.emptyList()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
