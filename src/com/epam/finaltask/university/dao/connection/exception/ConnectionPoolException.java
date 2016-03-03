package com.epam.finaltask.university.dao.connection.exception;


/**
 * Connection pool exception.
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
