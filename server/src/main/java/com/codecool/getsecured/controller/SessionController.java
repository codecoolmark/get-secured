package com.codecool.getsecured.controller;

import com.codecool.getsecured.TokenService;
import com.codecool.getsecured.model.User;
import com.codecool.getsecured.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SessionController {

    private final UserService userService;

    private final TokenService tokenService;

    public SessionController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/session")
    Optional<String> postSession(@RequestBody User loginData) {
        if (this.userService.validateLogin(loginData)) {
            return Optional.of(this.tokenService.generateToken(loginData));
        }

        return Optional.empty();
    }
}
