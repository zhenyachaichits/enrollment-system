package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class FacultyDaoImpl implements FacultyDao {

    private FacultyDaoImpl() {
    }

    public static class FacultyDaoHolder {
        public static final FacultyDaoImpl INSTANCE = new FacultyDaoImpl();
    }

    public static FacultyDaoImpl getInstance() {
        return FacultyDaoHolder.INSTANCE;
    }

    @Override
    public Faculty add(Faculty entity) throws DaoException {
        return null;
    }

    @Override
    public Faculty find(Faculty domain) throws DaoException {
        return null;
    }

    @Override
    public Faculty update(Faculty entity) throws DaoException {
        return null;
    }

    @Override
    public Faculty delete(Faculty domain) throws DaoException {
        return null;
    }

    @Override
    public List<Faculty> all() throws DaoException {
        return null;
    }
}
