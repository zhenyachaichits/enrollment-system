package com.epam.finaltask.university.controller.command.exception;

import com.epam.finaltask.university.controller.command.Command;

/**
 * Created by Zheny Chaichits on 22.02.2016.
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
