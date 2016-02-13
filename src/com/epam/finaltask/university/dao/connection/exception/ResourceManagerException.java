package com.epam.finaltask.university.dao.connection.exception;

/**
 * Created by Zheny Chaichits on 01.02.16.
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
