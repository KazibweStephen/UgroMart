package com.ugromart.platform.user.models;

import lombok.Data;

@Data
public class Auth {
    private int userId;
    private String token;
    private String username;

    public Auth() {
    }

    public Auth(int userId, String username, String token) {
        this.userId = userId;
        this.username=username;
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
