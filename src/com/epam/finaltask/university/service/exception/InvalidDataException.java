package com.epam.finaltask.university.service.exception;

/**
 * Created by Zheny Chaichits on 10.02.2016.
 */
public class InvalidDataException extends Exception {
    public InvalidDataException() {
        super();
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Exception cause) {
        super(message, cause);
    }
}
