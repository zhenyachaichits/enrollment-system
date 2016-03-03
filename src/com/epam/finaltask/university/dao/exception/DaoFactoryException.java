package com.epam.finaltask.university.dao.exception;


/**
 * Dao factory exception.
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
