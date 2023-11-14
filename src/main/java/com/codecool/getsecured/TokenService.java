package com.codecool.getsecured;

import com.codecool.getsecured.model.User;
import com.codecool.getsecured.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final String secret;

    private final PasswordEncoder passwordEncoder;

    public TokenService(@Value("${token.secret}") String secret, PasswordEncoder passwordEncoder) {
        this.secret = secret;
        this.passwordEncoder = passwordEncoder;
    }


    public String generateToken(User user) {
        StringBuilder tokenBuilder = new StringBuilder();

        tokenBuilder.append(user.getUsername());
        tokenBuilder.append(";");
        tokenBuilder.append(this.passwordEncoder.encode(this.secret + user.getUsername()));

        return tokenBuilder.toString();
    }
}
