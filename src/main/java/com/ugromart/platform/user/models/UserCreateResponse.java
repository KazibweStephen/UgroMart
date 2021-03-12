package com.ugromart.platform.user.models;


public class UserCreateResponse {
    private String username;
    private long userId;

    public UserCreateResponse(String username, long userId) {
        this.username = username;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
