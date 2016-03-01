package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 01.03.2016.
 */
public class TermsService {

    private TermsService() { }

    public static class TermsServiceHolder {
        public static final TermsService INSTANCE = new TermsService();
    }

    public static TermsService getInstance() {
        return TermsServiceHolder.INSTANCE;
    }

    public List<Terms> getAllTerms() throws ServiceException {
        try {
            TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

            return dao.all();
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide subjects finding service", e);
        }
    }
}
