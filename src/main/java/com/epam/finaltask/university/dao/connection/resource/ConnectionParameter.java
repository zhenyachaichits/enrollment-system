package com.epam.finaltask.university.dao.connection.resource;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Connection parameter.
 */
public class ConnectionParameter {

    private static final Logger LOG = LogManager.getLogger(ConnectionParameter.class.getClass());

    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "db.poolsize";

    private static final int DEFAULT_POOL_SIZE = 5;

    private String driver;
    private String url;
    private String username;
    private String password;
    private String poolSize;

    public ConnectionParameter() {
        DBResourceManager rm = DBResourceManager.getInstance();
        driver = rm.getParameter(DRIVER);
        url = rm.getParameter(URL);
        username = rm.getParameter(USERNAME);
        password = rm.getParameter(PASSWORD);
        poolSize = rm.getParameter(POOL_SIZE);
    }


    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        try{
            return Integer.parseInt(poolSize);
        } catch (NumberFormatException e) {
            LOG.error("Invalid pool size. Default pool size was returned", e);
            return DEFAULT_POOL_SIZE;
        }
    }
}
