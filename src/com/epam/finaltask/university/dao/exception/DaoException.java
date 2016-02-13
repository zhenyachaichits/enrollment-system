package com.epam.finaltask.university.dao.exception;

/**
 * Created by Zheny Chaichits on 22.01.16.
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
