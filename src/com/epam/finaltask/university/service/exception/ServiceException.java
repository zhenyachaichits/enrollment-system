package com.epam.finaltask.university.service.exception;


/**
 * Created by Zheny Chaichits on 04.02.16.
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception cause) {
        super(message, cause);
    }
}
