package com.epam.finaltask.university.dao.factory;

import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.impl.SqlDaoFactory;
import com.epam.finaltask.university.dao.type.DaoType;

import java.util.ResourceBundle;

/**
 * Created by Zheny Chaichits on 24.01.16.
 */
public abstract class DaoFactory {

    private static final String CONFIG = "resources.dao_config";
    private static final String TYPE = "dao.type";

    public static DaoFactory getDaoFactory() throws DaoFactoryException {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(CONFIG);
            String type = bundle.getString(TYPE);

            DaoType daoType = DaoType.valueOf(type);

            switch (daoType) {
                case SQL:
                    return SqlDaoFactory.getInstance();
                default:
                    throw new DaoFactoryException("Factory doesn't have implementation");
            }

        } catch (IllegalArgumentException e) {
            throw new DaoFactoryException("Couldn't find factory. Dao type is incorrect.", e);
        }
    }

    public abstract UserDao getUserDao() throws DaoException;

    public abstract ProfileDao getProfileDao() throws DaoException;

}