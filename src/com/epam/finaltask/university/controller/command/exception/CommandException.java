package com.epam.finaltask.university.controller.command.exception;

/**
 * Created by Zheny Chaichits on 25.12.2015.
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
