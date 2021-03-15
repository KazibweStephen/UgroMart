package com.ugromart.platform.order.exceptions;

import com.ugromart.platform.configuration.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Class<?> clazz, long id) {
        super(clazz, id);
    }

    public ProductNotFoundException(Class<?> clazz, String id) {
        super(clazz, id);
    }
}
