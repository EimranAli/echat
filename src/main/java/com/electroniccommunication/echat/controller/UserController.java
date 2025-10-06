package com.electroniccommunication.echat.controller;

import com.electroniccommunication.echat.model.User;
import com.electroniccommunication.echat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/echat")
public class UserController {
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @PostMapping("/users/register-user")
    public HttpStatus registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
