package com.epam.finaltask.university.listener;

import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.connection.exception.ResourceManagerException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Zheny Chaichits on 04.02.16.
 */
public class ServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            connectionPool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();  //todo: logging ect
        }
    }

    public void contextDestroyed(ServletContextEvent arg) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            connectionPool.destroy();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
