package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.TermsService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.validator.TermsValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Locking terms service.
 */
public class LockingTermsService {

    private LockingTermsService() {
    }

    public static class LockingTermsServiceHolder {
        public static final LockingTermsService INSTANCE = new LockingTermsService();
    }

    public static LockingTermsService getInstance() {
        return LockingTermsServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    /**
     * Create terms.
     *
     * @param terms the terms
     * @return the terms
     * @throws ServiceException the service exception
     */
    public Terms createTerms(Terms terms) throws ServiceException {
        TermsService service = TermsService.getInstance();

        if(TermsValidator.validateTerms(terms) && !service.checkTermsExistence(terms)) {
            lock.lock();
            try {
                TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

                return dao.add(terms);
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide terms creating service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid terms data. Operation Stopped");
        }
    }

    /**
     * Update terms.
     *
     * @param terms the terms
     * @return the terms
     * @throws ServiceException the service exception
     */
    public Terms updateTerms(Terms terms) throws ServiceException {
        TermsService service = TermsService.getInstance();

        if(TermsValidator.validateTerms(terms) && service.checkUpdateAvailability(terms)) {
            lock.lock();
            try {
                TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

                return dao.update(terms);
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide terms updating service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid terms data. Operation Stopped");
        }
    }

    /**
     * Delete terms.
     *
     * @param termsId the terms id
     * @return true if deleted, else false
     * @throws ServiceException the service exception
     */
    public boolean deleteTerms(long termsId) throws ServiceException {
        lock.lock();
        try {
            TermsDao dao = DaoFactory.getDaoFactory().getTermsDao();

            return dao.delete(termsId) != null;
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide terms deletion service");
        } finally {
            lock.unlock();
        }
    }

}
