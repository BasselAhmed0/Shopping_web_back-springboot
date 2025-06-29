package com.shop.shop.Controller;

import com.shop.shop.JWTConfigurations.AuthenticationRequest;
import com.shop.shop.JWTConfigurations.AuthenticationResponse;
import com.shop.shop.JWTConfigurations.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManagerBean;

    @Autowired
    JWTUtils jwtUtils;

    @PostMapping("/signin")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest, @RequestHeader(name = "Host") String host, @RequestHeader(name = "User-Agent") String agent) {
        authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        return jwtUtils.generateToken(authenticationRequest.getUsername(), host, agent);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Retrieve the username from the currently authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        jwtUtils.invalidateUserTokens(username); // Invalidate all tokens for the user
        return ResponseEntity.noContent().build(); // Respond with a 204 No Content status
    }

}