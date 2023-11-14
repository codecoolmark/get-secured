package com.codecool.getsecured.services;

import com.codecool.getsecured.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Collection<User> getAllUsers();

    Optional<User> createUser(User newUser);

    boolean validateLogin(User loginData);
}
