package com.ugromart.platform.configuration;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Class<?> clazz, long id) {
        super(clazz, id);
    }

    public UserNotFoundException(Class<?> clazz, String id) {
        super(clazz, id);
    }
}
