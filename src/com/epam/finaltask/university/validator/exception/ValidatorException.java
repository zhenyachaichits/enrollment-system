package com.epam.finaltask.university.validator.exception;

/**
 * Created by Zheny Chaichits on 10.02.2016.
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
