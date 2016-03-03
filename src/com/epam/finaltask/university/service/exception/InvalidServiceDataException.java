package com.epam.finaltask.university.service.exception;


/**
 * Invalid service data exception.
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
