package com.shop.shop.JWTConfigurations;

import com.shop.shop.Security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityWebFilter extends OncePerRequestFilter {
    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        String host = request.getHeader("Host");
        String userAgent = request.getHeader("User-Agent");
        String jwtToken = null;
        if (token != null && token.startsWith("Bearer ")) {
            jwtToken = token.replace("Bearer ", "");
        }

        if (jwtToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            boolean isValid = jwtUtils.validateToken(jwtToken, host, userAgent);

            //get user from token
            if (isValid) {
                String username = jwtUtils.getUserFromToken(jwtToken);

                UserDetails user = customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken token1 = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                token1.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token1);
            }
        }

        filterChain.doFilter(request, response);
    }
}
