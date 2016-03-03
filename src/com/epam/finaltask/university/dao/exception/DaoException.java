package com.epam.finaltask.university.dao.exception;


/**
 * Dao exception.
 */
public class DaoException extends Exception {
    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Exception cause){
        super(message, cause);
    }
}
