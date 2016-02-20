package com.epam.finaltask.university.controller.command.exception;

/**
 * Created by Zheny Chaichits on 11.02.2016.
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
