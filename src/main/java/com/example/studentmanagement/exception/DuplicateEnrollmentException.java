package com.example.studentmanagement.exception;

/**
 * This exception is thrown when a student is enrolled into a course they are already enrolled in.
 */
public class DuplicateEnrollmentException extends RuntimeException {

    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}
