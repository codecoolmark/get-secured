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

    public boolean validateToken(String token) {
        var username = this.extractUsername(token);
        var hash = this.extractHash(token);
        return this.passwordEncoder.matches(this.secret + username, hash);
    }

    public String username(String token) {
        if (!validateToken(token)) {
            throw new IllegalArgumentException("Provided token is invalid!");
        }
        return this.extractUsername(token);
    }

    private String extractUsername(String token) {
        var firstSemicolonIndex = token.indexOf(";");
        return token.substring(0, firstSemicolonIndex);
    }

    private String extractHash(String token) {
        var firstSemicolonIndex = token.indexOf(";");
        return token.substring(firstSemicolonIndex + 1);
    }
}
