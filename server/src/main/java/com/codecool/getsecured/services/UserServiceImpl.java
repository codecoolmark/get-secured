package com.codecool.getsecured.services;

import com.codecool.getsecured.model.User;
import com.codecool.getsecured.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Collection<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    @Override
    public Optional<User> createUser(User newUser) {
        var hashedPassword = this.passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        this.userRepository.addUser(newUser);
        return Optional.of(newUser);
    }

    @Override
    public boolean validateLogin(User loginData) {
        var user = this.userRepository.findUserByUsername(loginData.getUsername());

        return this.passwordEncoder.matches(loginData.getPassword(), user.getPassword());
    }
}
