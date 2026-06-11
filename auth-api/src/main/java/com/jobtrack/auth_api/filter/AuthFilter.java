package com.jobtrack.auth_api.filter;

import com.jobtrack.auth_api.model.AppUser;
import com.jobtrack.auth_api.service.UserService;
import com.jobtrack.auth_api.utility.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtility jwtUtility;

    public void doFilterInternal(HttpServletRequest request, //The incoming HTTP request (headers, body, URI)
                                 HttpServletResponse response, //The HTTP response you can modify or write to
                                 FilterChain filterChain // the link to the next filter in the security chain
                                 ) throws ServletException, IOException {

        String bearerToken =request.getHeader("Authorization");

        if(bearerToken == null || !bearerToken.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = bearerToken.substring(7);
            String email = jwtUtility.verifyJwt(token);

            AppUser appUser = userService.findUserByEmail(email);

            if (appUser == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        appUser.getEmail(),
                        null,
                        Collections.emptyList()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }




}
