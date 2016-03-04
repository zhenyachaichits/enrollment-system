package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;


/**
 * Terms service.
 */
public class TermsService {

    private TermsService() { }

    public static class TermsServiceHolder {
        public static final TermsService INSTANCE = new TermsService();
    }

    public static TermsService getInstance() {
        return TermsServiceHolder.INSTANCE;
    }

    /**
     * Gets all terms.
     *
     * @return the all terms
     * @throws ServiceException the service exception
     */
    public List<Terms> getAllTerms() throws ServiceException {
        try {
            TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

            return dao.all();
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide subjects finding service", e);
        }
    }

    /**
     * Check terms existence.
     *
     * @param terms the terms
     * @return true if exists, else false
     * @throws ServiceException the service exception
     */
    public boolean checkTermsExistence(Terms terms) throws ServiceException {
        try {
            TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

            return dao.checkExistence(terms);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide terms checking service", e);
        }
    }

    /**
     * Check update availability.
     *
     * @param terms the terms
     * @return true if available, else false
     * @throws ServiceException the service exception
     */
    public boolean checkUpdateAvailability(Terms terms) throws ServiceException {
        try {
            TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

            return dao.checkUpdateAvailability(terms);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide terms checking service", e);
        }
    }
}
