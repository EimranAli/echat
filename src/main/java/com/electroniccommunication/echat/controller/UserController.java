package com.electroniccommunication.echat.controller;

import com.electroniccommunication.echat.config.JWTGenerator;
import com.electroniccommunication.echat.model.User;
import com.electroniccommunication.echat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/echat")
public class UserController {
    private final JWTGenerator jwtGenerator;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @GetMapping("/users/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String hashedPassword) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        HttpStatus status = isValidCredentials(userDetails, hashedPassword) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String bearerToken = jwtGenerator.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        return new ResponseEntity<>(bearerToken, status);
    }

    @PostMapping("/users/register-user")
    public HttpStatus registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    private boolean isValidCredentials(UserDetails user, String hashedPassword) {
        return Optional.ofNullable(user).map(_user -> hashedPassword.equals(_user.getPassword())).orElse(false);
    }
}
