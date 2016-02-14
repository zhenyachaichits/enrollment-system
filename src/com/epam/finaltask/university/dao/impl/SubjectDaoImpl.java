package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class SubjectDaoImpl implements SubjectDao {

    private SubjectDaoImpl() {
    }

    public static class SubjectDaoHolder {
        public static final SubjectDaoImpl INSTANCE = new SubjectDaoImpl();
    }

    public static SubjectDaoImpl getInstance() {
        return SubjectDaoHolder.INSTANCE;
    }

    @Override
    public Subject add(Subject entity) throws DaoException {
        return null;
    }

    @Override
    public Subject find(Subject domain) throws DaoException {
        return null;
    }

    @Override
    public Subject update(Subject entity) throws DaoException {
        return null;
    }

    @Override
    public Subject delete(Subject domain) throws DaoException {
        return null;
    }

    @Override
    public List<Subject> all() throws DaoException {
        return null;
    }
}
