package com.epam.finaltask.university.validator.exception;


/**
 * Validator exception.
 */
public class ValidatorException extends Exception {

    public ValidatorException() {
        super();
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Exception cause) {
        super(message, cause);
    }
}
