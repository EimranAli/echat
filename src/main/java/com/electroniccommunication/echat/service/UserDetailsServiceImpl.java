package com.electroniccommunication.echat.service;

import com.electroniccommunication.echat.model.Roles;
import com.electroniccommunication.echat.model.User;
import com.electroniccommunication.echat.model.UserDetailsImpl;
import com.electroniccommunication.echat.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        return user.map(this::mapToUserDetails).orElse(null);
    }

    private UserDetails mapToUserDetails(User user) {
        return UserDetailsImpl.builder()
                .username(user.getEmail())
                .password(user.getHashedPassword())
                .authorities(List.of(new SimpleGrantedAuthority(Roles.USER.toString())))
                .build();
    }
}
