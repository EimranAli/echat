package com.electroniccommunication.echat.controller;

import com.electroniccommunication.echat.model.User;
import com.electroniccommunication.echat.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@AllArgsConstructor
@RestController()
@CrossOrigin
@RequestMapping("/echat")
public class UserController {
    private final UserService userService;

    @GetMapping("/users/get-user")
    public ResponseEntity<User> getUser(@RequestParam String email, @RequestParam String hashedPassword) {
        User user = userService.getUser(email, hashedPassword);
        HttpStatus status = Objects.nonNull(user) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(user, status);
    }

    @PostMapping("/users/register-user")
    public HttpStatus registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
