package com.ugromart.platform.order.exceptions;

import javax.validation.ValidationException;

public class OrderValidationException extends ValidationException {
    public OrderValidationException(String message) {
        super(message);
    }

    public OrderValidationException() {
    }

    public OrderValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderValidationException(Throwable cause) {
        super(cause);
    }
}
