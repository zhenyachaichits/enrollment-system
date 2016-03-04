package com.epam.finaltask.university.dao.factory;

import com.epam.finaltask.university.dao.*;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.impl.SqlDaoFactory;
import com.epam.finaltask.university.dao.type.DaoType;

import java.util.ResourceBundle;


/**
 * Dao factory.
 */
public abstract class DaoFactory {

    private static final String CONFIG = "dao_config";
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

    public abstract SubjectDao getSubjectDao() throws DaoException;

    public abstract FacultyDao getFacultyDao() throws DaoException;

    public abstract StudentDao getStudentDao() throws DaoException;

    public abstract ApplicationDao getApplicationDao() throws DaoException;

    public abstract TermsDao getTermsDao() throws DaoException;

}
