package com.example.studentmanagement.exception;

/**
 * This exception is thrown when an email already exists in the system.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
