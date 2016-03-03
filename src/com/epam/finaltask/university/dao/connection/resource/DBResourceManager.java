package com.epam.finaltask.university.dao.connection.resource;

import java.util.ResourceBundle;


/**
 * Database resource manager.
 */
public class DBResourceManager {

    public static final DBResourceManager instance = new DBResourceManager();

    private static final String CONFIG = "resources.db_config";

    private ResourceBundle bundle;

    private DBResourceManager() {
        bundle = ResourceBundle.getBundle(CONFIG);
    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    /**
     * Gets parameter.
     *
     * @param name the name
     * @return the parameter
     */
    public String getParameter(String name) /*throws ResourceManagerException*/ {
        return bundle.getString(name);
    }
}