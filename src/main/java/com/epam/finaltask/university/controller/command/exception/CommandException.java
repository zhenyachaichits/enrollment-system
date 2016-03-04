package com.epam.finaltask.university.controller.command.exception;


/**
 * The type Command exception.
 */
public class CommandException extends Exception {

    public CommandException(){
        super();
    }

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Exception cause){
        super(message, cause);
    }
}
