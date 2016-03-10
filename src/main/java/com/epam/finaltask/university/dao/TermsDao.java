package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.exception.DaoException;


/**
 * The interface Terms dao.
 */
public interface TermsDao extends EntityDao<Long, Terms> {
    /**
     * Check terms existence.
     *
     * @param terms the terms
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkExistence(Terms terms) throws DaoException;

    /**
     * Check update availability.
     *
     * @param terms the terms
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkUpdateAvailability(Terms terms) throws DaoException;

    boolean isCurrentDateInTerms(long termsId) throws DaoException;
}
