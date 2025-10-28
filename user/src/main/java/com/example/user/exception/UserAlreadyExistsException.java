package com.example.user.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String usernameAlreadyPresent) {
        super(usernameAlreadyPresent);
    }
}
