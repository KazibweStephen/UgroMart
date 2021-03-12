package com.ugromart.platform.user.services;

import com.ugromart.platform.user.models.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findUserById(long id);
}
