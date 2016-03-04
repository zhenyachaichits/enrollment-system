package com.epam.finaltask.university.controller.command.exception;


/**
 * The type Invalid data exception.
 */
public class InvalidDataException extends CommandException {

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
