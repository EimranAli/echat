package com.electroniccommunication.echat.service;

import com.electroniccommunication.echat.model.User;
import com.electroniccommunication.echat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    public HttpStatus registerUser(User user) {
        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }

    public User getUser(String email, String hashedPassword) {
        Optional<User> user = userRepository.findById(email);
        // remove password for security reasons
        user.ifPresent(u -> u.setHashedPassword(null));
        return user.orElse(null);
    }
}

