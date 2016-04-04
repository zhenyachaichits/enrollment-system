package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;


/**
 * The interface Application dao.
 */
public interface ApplicationDao extends EntityDao<Long, Application> {
    /**
     * Find out of competition applications number.
     *
     * @param facultyId  the faculty id
     * @param isFreeForm the is free form
     * @return number of applications
     * @throws DaoException the dao exception
     */
    int findOutCompetitionNumber(long facultyId, boolean isFreeForm) throws DaoException;

    /**
     * Find min passing points for applications by parameters.
     *
     * @param facultyId          the faculty id
     * @param quota              the quota
     * @param isOutOfCompetition the is out of competition
     * @param isFreeForm         the is free form
     * @return minimal passed points
     * @throws DaoException the dao exception
     */
    int findMinPoints(long facultyId, int quota, boolean isOutOfCompetition, boolean isFreeForm) throws DaoException;

    /**
     * Confirms application.
     *
     * @param profileId the profile id
     * @return true if application confirmed, else false
     * @throws DaoException the dao exception
     */
    boolean confirmApplication(long profileId) throws DaoException;

    boolean checkDeletionAvailability(long profileId) throws DaoException;

    boolean deleteUserProfile(long userId) throws DaoException;

    boolean resetFacultyApplications(long facultyId) throws DaoException;
}
