package com.epam.finaltask.university.listener;

import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Servlet listener.
 */
public class StartUpListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            connectionPool.init();
        } catch (ConnectionPoolException e) {
            //todo: logging ect
        }
    }

    public void contextDestroyed(ServletContextEvent arg) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
    }
}
