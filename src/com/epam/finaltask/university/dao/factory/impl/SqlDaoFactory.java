package com.epam.finaltask.university.dao.factory.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.dao.impl.FacultyDaoImpl;
import com.epam.finaltask.university.dao.impl.ProfileDaoImpl;
import com.epam.finaltask.university.dao.impl.SubjectDaoImpl;
import com.epam.finaltask.university.dao.impl.UserDaoImpl;

/**
 * Created by Zheny Chaichits on 22.01.16.
 */
public class SqlDaoFactory extends DaoFactory {
    private static final SqlDaoFactory instance = new SqlDaoFactory();

    private SqlDaoFactory() {}

    public static SqlDaoFactory getInstance() {
        return instance;
    }

    @Override
    public UserDao getUserDao() throws DaoException {
        return UserDaoImpl.getInstance();
    }

    @Override
    public ProfileDao getProfileDao() throws DaoException {
        return ProfileDaoImpl.getInstance();
    }

    @Override
    public SubjectDao getSubjectDao() throws DaoException {
        return SubjectDaoImpl.getInstance();
    }

    @Override
    public FacultyDao getFacultyDao() throws DaoException {
        return FacultyDaoImpl.getInstance();
    }

}
