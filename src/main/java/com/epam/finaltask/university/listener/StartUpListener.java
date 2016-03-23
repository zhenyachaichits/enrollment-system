package com.epam.finaltask.university.listener;

import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * Servlet listener.
 */
public class StartUpListener implements ServletContextListener {

    private static final Logger LOG = LogManager.getLogger(StartUpListener.class.getClass());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            LOG.error("Couldn't start up servlet correctly, because connection pool wasn't initialized", e);
        }
    }

    public void contextDestroyed(ServletContextEvent arg) {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.destroy();
        } catch (ConnectionPoolException e) {
            LOG.error("Couldn't end up servlet correctly, because connection pool wasn't destroyed", e);
        }
    }
}
