package com.epam.finaltask.university.dao.connection.exception;


/**
 * Resource manager exception.
 */
public class ResourceManagerException extends Exception {
    public ResourceManagerException() {
        super();
    }

    public ResourceManagerException(String message) {
        super(message);
    }

    public ResourceManagerException(String message, Exception cause) {
        super(message, cause);
    }
}
