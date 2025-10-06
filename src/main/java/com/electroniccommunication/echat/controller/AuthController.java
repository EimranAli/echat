package com.electroniccommunication.echat.controller;

import com.electroniccommunication.echat.jwt.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public AuthController(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String hashedPassword) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        HttpStatus status = isValidCredentials(userDetails, hashedPassword) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String bearerToken = jwtUtils.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        return new ResponseEntity<>(bearerToken, status);
    }

    private boolean isValidCredentials(UserDetails user, String hashedPassword) {
        return Optional.ofNullable(user).map(_user -> hashedPassword.equals(_user.getPassword())).orElse(false);
    }
}
