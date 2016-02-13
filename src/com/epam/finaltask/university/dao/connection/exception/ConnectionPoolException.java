package com.epam.finaltask.university.dao.connection.exception;

/**
 * Created by Zheny Chaichits on 01.02.16.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(){
        super();
    }

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(String message, Exception cause){
        super(message, cause);
    }
}
