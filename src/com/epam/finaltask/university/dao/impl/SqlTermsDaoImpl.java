package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class SqlTermsDaoImpl implements TermsDao {

    private SqlTermsDaoImpl() {
    }

    public static class TermsDaoHolder {
        public static final SqlTermsDaoImpl INSTANCE = new SqlTermsDaoImpl();
    }

    public static SqlTermsDaoImpl getInstance() {
        return TermsDaoHolder.INSTANCE;
    }

    @Override
    public Terms add(Terms entity) throws DaoException {
        return null;
    }

    @Override
    public Terms find(Terms domain) throws DaoException {
        return null;
    }

    @Override
    public Terms update(Terms entity) throws DaoException {
        return null;
    }

    @Override
    public Terms delete(Terms domain) throws DaoException {
        return null;
    }

    @Override
    public List<Terms> all() throws DaoException {
        return null;
    }
}
