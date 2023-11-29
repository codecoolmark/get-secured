package com.codecool.getsecured.controller;

import com.codecool.getsecured.model.User;
import com.codecool.getsecured.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userServices;

    public UserController(UserService userService) {
        this.userServices = userService;
    }

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return this.userServices.getAllUsers();
    }

    @PostMapping("/users")
    public Optional<User> postUsers(@RequestBody User newUser) {
        return this.userServices.createUser(newUser);
    }


}
