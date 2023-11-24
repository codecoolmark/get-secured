package com.codecool.getsecured.repository;

import com.codecool.getsecured.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Repository
class UserRepositoryImpl implements UserRepository {
    private final List<User> users;

    public UserRepositoryImpl() {
        this.users = new LinkedList<>();
    }

    @Override
    public User findUserByUsername(String username) {
        return users.stream()
                .filter(user -> Objects.equals(user.getUsername(), username))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Collection<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User addUser(User newUser) {
        this.users.add(newUser);
        return newUser;
    }
}
