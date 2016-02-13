package com.epam.finaltask.university.dao.exception;

/**
 * Created by Zheny Chaichits on 26.01.16.
 */
public class DaoFactoryException extends Exception {
    public DaoFactoryException(){
        super();
    }

    public DaoFactoryException(String message){
        super(message);
    }

    public DaoFactoryException(String message, Exception cause){
        super(message, cause);
    }
}
