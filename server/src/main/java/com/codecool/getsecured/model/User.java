package com.codecool.getsecured.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User  {

    private String password;

    private final String username;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
