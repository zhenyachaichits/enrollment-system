package com.epam.finaltask.university.controller.command.exception;


/**
 * The type Access denied exception.
 */
public class AccessDeniedException extends CommandException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Exception cause) {
        super(message, cause);
    }
}
