package com.epam.finaltask.university.service.exception;

/**
 * Created by Zheny Chaichits on 10.02.2016.
 */
public class InvalidServiceDataException extends ServiceException {
    public InvalidServiceDataException() {
        super();
    }

    public InvalidServiceDataException(String message) {
        super(message);
    }

    public InvalidServiceDataException(String message, Exception cause) {
        super(message, cause);
    }
}
