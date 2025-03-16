package com.electroniccommunication.echat.service;

import com.electroniccommunication.echat.model.User;
import com.electroniccommunication.echat.model.UserDetailsImpl;
import com.electroniccommunication.echat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        UserDetailsImpl userDetails = new UserDetailsImpl();
        user.ifPresent(domainUser -> map(userDetails, domainUser));
        return userDetails;
    }

    private void map(UserDetailsImpl userDetails, User user) {
        userDetails.setUsername(user.getEmail());
        userDetails.setPassword(user.getHashedPassword());

        // at present, consider only user roles
        userDetails.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
