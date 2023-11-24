package com.codecool.getsecured.controller;

import com.codecool.getsecured.TokenService;
import com.codecool.getsecured.model.User;
import com.codecool.getsecured.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userServices;

    private final TokenService tokenService;

    public UserController(UserService userService, TokenService tokenService) {
        this.userServices = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/users")
    public Collection<User> getUsers(@RequestHeader("Authentication") String token) throws IllegalAccessException {

        if (!this.tokenService.validateToken(token)) {
            throw new IllegalAccessException("Forbidden");
        }

        return this.userServices.getAllUsers();
    }

    @PostMapping("/users")
    public Optional<User> postUsers(@RequestBody User newUser) {
        return this.userServices.createUser(newUser);
    }


}
