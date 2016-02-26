package com.epam.finaltask.university.controller.util.compiler.exception;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class BeanCompilerException extends Exception {

    public BeanCompilerException(){
        super();
    }

    public BeanCompilerException(String message){
        super(message);
    }

    public BeanCompilerException(String message, Exception cause){
        super(message, cause);
    }
}
