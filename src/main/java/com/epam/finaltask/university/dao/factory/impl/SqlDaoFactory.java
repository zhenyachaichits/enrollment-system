package com.epam.finaltask.university.dao.factory.impl;

import com.epam.finaltask.university.dao.*;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.dao.impl.*;


/**
 * Sql dao factory. Contains methods returning Dao implementation instances.
 */
public class SqlDaoFactory extends DaoFactory {
    private static final SqlDaoFactory instance = new SqlDaoFactory();

    private SqlDaoFactory() {}

    public static SqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getUserDao() throws DaoException {
        return SqlUserDaoImpl.getInstance();
    }

    @Override
    public ProfileDao getProfileDao() throws DaoException {
        return SqlProfileDaoImpl.getInstance();
    }

    @Override
    public SubjectDao getSubjectDao() throws DaoException {
        return SqlSubjectDaoImpl.getInstance();
    }

    @Override
    public FacultyDao getFacultyDao() throws DaoException {
        return SqlFacultyDaoImpl.getInstance();
    }

    @Override
    public StudentDao getStudentDao() throws DaoException {
        return SqlStudentDaoImpl.getInstance();
    }

    @Override
    public ApplicationDao getApplicationDao() throws DaoException {
        return SqlApplicationDaoImpl.getInstance();
    }

    @Override
    public TermsDao getTermsDao() throws DaoException {
        return SqlTermsDaoImpl.getInstance();
    }


}
