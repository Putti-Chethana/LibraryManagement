package com.example.library.exception;

public class BookNotAllotedToUserException extends RuntimeException{
    public BookNotAllotedToUserException(String message) {
        super(message);
    }
}
