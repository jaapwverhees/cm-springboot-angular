package com.verhees.cm.service;

import com.verhees.cm.model.user.User;
import com.verhees.cm.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ADMIN";
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User registerDefaultUser(User user) {
        setPasswordAndRoleDefaultUser(user);
        return userRepository.save(user);
    }

    private void setPasswordAndRoleDefaultUser(User user) {
        user.getUserCredentials()
                .setPassword(encoder.encode(user.getUserCredentials().getPassword()));
        user.getUserCredentials().setRole(DEFAULT_ROLE);
    }

    public User registerAdmin(User user) {
        setPasswordAndRoleAdmin(user);
        return userRepository.save(user);
    }

    private void setPasswordAndRoleAdmin(User user) {
        user.getUserCredentials()
                .setPassword(encoder.encode(user.getUserCredentials().getPassword()));
        user.getUserCredentials().setRole(ADMIN_ROLE);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserCredentialsUsername(username);
    }

}
