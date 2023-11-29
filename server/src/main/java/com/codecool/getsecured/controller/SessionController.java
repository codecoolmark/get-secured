package com.codecool.getsecured.controller;

import com.codecool.getsecured.model.User;
import com.codecool.getsecured.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SessionController {

    private final UserService userService;

    private final AuthenticationProvider authenticationProvider;

    private final JwtEncoder jwtEncoder;

    public SessionController(UserService userService,
                             AuthenticationProvider authenticationProvider,
                             JwtEncoder jwtEncoder) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/session")
    Optional<String> postSession(@RequestBody User loginData, @Value("${tokens.algorithm}") MacAlgorithm macAlgorithm) {
        var usernamePasswordAuthentication =
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword());

        var authenticatedAuthentication = this.authenticationProvider.authenticate(usernamePasswordAuthentication);

        if (authenticatedAuthentication.isAuthenticated()) {
            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(JwsHeader.with(macAlgorithm).build(),
                    JwtClaimsSet.builder()
                    .subject(authenticatedAuthentication.getName())
                    .build()));
            return Optional.of(token.getTokenValue());
        }

        return Optional.empty();
    }
}
