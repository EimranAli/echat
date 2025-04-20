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
        if(userRepository.existsById(user.getEmail()))
            return HttpStatus.CONFLICT;

        userRepository.save(user);
        return HttpStatus.ACCEPTED;
    }
}

