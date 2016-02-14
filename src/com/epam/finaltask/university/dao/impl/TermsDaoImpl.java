package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class TermsDaoImpl implements TermsDao {

    private TermsDaoImpl() {
    }

    public static class TermsDaoHolder {
        public static final TermsDaoImpl INSTANCE = new TermsDaoImpl();
    }

    public static TermsDaoImpl getInstance() {
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
