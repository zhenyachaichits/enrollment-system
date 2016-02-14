package com.epam.finaltask.university.dao.connection.resource;

import com.epam.finaltask.university.dao.connection.exception.ResourceManagerException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Created by Zheny Chaichits on 01.02.16.
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

    public String getParameter(String name) /*throws ResourceManagerException*/ {
        return bundle.getString(name);

        /*try {
        return bundle.getString(name);
        } catch (MissingResourceException e) {
            throw new ResourceManagerException("Couldn't find parameter by name", e);
        }*/
    }
}