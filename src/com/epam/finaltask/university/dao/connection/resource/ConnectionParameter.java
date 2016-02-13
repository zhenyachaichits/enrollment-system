package com.epam.finaltask.university.dao.connection.resource;

import com.epam.finaltask.university.dao.connection.exception.ResourceManagerException;

/**
 * Created by Zheny Chaichits on 01.02.16.
 */
public final class ConnectionParameter {

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
    private String poolsize;

    public ConnectionParameter() throws ResourceManagerException {
        DBResourceManager rm = DBResourceManager.getInstance();
        driver = rm.getParameter(DRIVER);
        url = rm.getParameter(URL);
        username = rm.getParameter(USERNAME);
        password = rm.getParameter(PASSWORD);
        poolsize = rm.getParameter(POOL_SIZE);
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

    public int getPoolsize() {
        try{
            return Integer.parseInt(poolsize);
        } catch (NumberFormatException e) {
         //TODO: add logger
            return DEFAULT_POOL_SIZE;
        }
    }
}
