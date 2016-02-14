package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.dao.ApplicationDao;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class ApplicationDaoImpl implements ApplicationDao {

    private ApplicationDaoImpl() {
    }

    public static class ApplicationDaoHolder {
        public static final ApplicationDaoImpl INSTANCE = new ApplicationDaoImpl();
    }

    public static ApplicationDaoImpl getInstance() {
        return ApplicationDaoHolder.INSTANCE;
    }

    @Override
    public Application add(Application entity) throws DaoException {
        return null;
    }

    @Override
    public Application find(Application domain) throws DaoException {
        return null;
    }

    @Override
    public Application update(Application entity) throws DaoException {
        return null;
    }

    @Override
    public Application delete(Application domain) throws DaoException {
        return null;
    }

    @Override
    public List<Application> all() throws DaoException {
        return null;
    }
}
