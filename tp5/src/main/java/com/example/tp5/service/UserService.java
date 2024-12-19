package com.example.tp5.service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tp5.entity.User;
import com.example.tp5.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Fetch user by username
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    // Create a new user
    public User createUser(String username, String password, List<String> roles) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles.stream().map(roleName -> new Role(roleName)).collect(Collectors.toList()));

        return userRepository.save(user);
    }

    // Update user password
    public User updatePassword(String username, String newPassword) {
        User user = findUserByUsername(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(String username) {
        User user = findUserByUsername(username);
        userRepository.delete(user);
    }
}
