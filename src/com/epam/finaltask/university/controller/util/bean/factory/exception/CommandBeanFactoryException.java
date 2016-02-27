package com.epam.finaltask.university.controller.util.bean.factory.exception;

/**
 * Created by Zheny Chaichits on 25.02.2016.
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
