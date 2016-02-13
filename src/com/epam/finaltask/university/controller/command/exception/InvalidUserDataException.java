package com.epam.finaltask.university.controller.command.exception;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class InvalidUserDataException extends CommandException {

    public InvalidUserDataException() {
        super();
    }

    public InvalidUserDataException(String message) {
        super(message);
    }

    public InvalidUserDataException(String message, Exception cause) {
        super(message, cause);
    }
}
