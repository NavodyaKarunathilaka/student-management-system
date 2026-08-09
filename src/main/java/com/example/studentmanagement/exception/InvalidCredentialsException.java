package com.example.studentmanagement.exception;

/**
 * This exception is thrown when invalid credentials are provided during authentication.
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
