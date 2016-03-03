package com.epam.finaltask.university.controller.command.exception;

/**
 * The type Invalid session exception.
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
