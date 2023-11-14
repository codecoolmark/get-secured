package com.codecool.getsecured.repository;

import com.codecool.getsecured.model.User;

import java.util.Collection;

public interface UserRepository {
    User findUserByUsername(String username);

    Collection<User> getAllUsers();

    User addUser(User newUser);
}
