package com.epam.finaltask.university.controller.util.bean.factory.exception;


/**
 * Command bean factory exception.
 */
public class CommandBeanFactoryException extends Exception {

    public CommandBeanFactoryException(){
        super();
    }

    public CommandBeanFactoryException(String message){
        super(message);
    }

    public CommandBeanFactoryException(String message, Exception cause){
        super(message, cause);
    }
}
