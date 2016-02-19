package com.epam.finaltask.university.controller.command.exception;

/**
 * Created by Zheny Chaichits on 20.02.2016.
 */
public class InvalidSessionException extends CommandException {

    public InvalidSessionException() {
        super();
    }

    public InvalidSessionException(String message) {
        super(message);
    }

    public InvalidSessionException(String message, Exception cause) {
        super(message, cause);
    }
}
