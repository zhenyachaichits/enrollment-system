package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public interface ApplicationDao extends EntityDao<Long, Application> {
    int findOutCompetitionNumber(long facultyId, boolean isFreeForm) throws DaoException;

    int findMinPoints(long facultyId, int quota, boolean isOutOfCompetition, boolean isFreeForm) throws DaoException;

    boolean confirmApplication(long profileId) throws DaoException;
}
