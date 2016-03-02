package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.dao.ApplicationDao;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class ApplicationService {

    private ApplicationService() { }

    public static class ApplicationServiceHolder {
        public static final ApplicationService INSTANCE = new ApplicationService();
    }

    public static ApplicationService getInstance() {
        return ApplicationServiceHolder.INSTANCE;
    }

    private static final boolean OUT_COMPETITION = true;

    public boolean checkProfileIdExistence(long profileId) throws ServiceException {
        try {
            ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

            return dao.find(profileId) != null;
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide application existence checking service", e);
        }
    }

    public Application findApplicationByProfileId(long profileId) throws ServiceException {
        try {
            ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

            return dao.find(profileId);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide application finding service", e);
        }
    }

    private int findOutCompetitionNumber(long facultyId, boolean isFreeForm) throws ServiceException {
        try {
            ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

            return dao.findOutCompetitionNumber(facultyId, isFreeForm);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide application finding service", e);
        }
    }

    private int findPassingPoints(long facultyId, boolean isOutCompetition, boolean isFreeForm, int limit)
            throws ServiceException {
        try {
            ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

            return dao.findMinPoints(facultyId, limit, isOutCompetition, isFreeForm);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide application finding service", e);
        }
    }

    public int findPassingPoints(long facultyId, boolean isFreeForm, int quota) throws ServiceException {
        int outCompetition = findOutCompetitionNumber(facultyId, true);

        int passingPoint;
        if(outCompetition < quota) {
            passingPoint = findPassingPoints(facultyId, !OUT_COMPETITION, isFreeForm, quota - outCompetition);
        } else {
            passingPoint = findPassingPoints(facultyId, OUT_COMPETITION, isFreeForm, quota);
        }

        return passingPoint;
    }

}
