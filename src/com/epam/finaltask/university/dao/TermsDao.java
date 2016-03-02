package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.exception.DaoException;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public interface TermsDao extends EntityDao<Long, Terms> {
    boolean checkExistence(Terms terms) throws DaoException;

    boolean checkUpdateAvailability(Terms terms) throws DaoException;
}
