package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;


/**
 * The interface Profile dao.
 */
public interface ProfileDao extends EntityDao<String, Profile> {
    /**
     * Find profile by last name.
     *
     * @param lastName     the last name
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list of profiles
     * @throws DaoException the dao exception
     */
    List<Profile> findProfileByLastName(String lastName, int offset, int recordsCount) throws DaoException;

    /**
     * Find by id profile.
     *
     * @param id the id
     * @return the profile
     * @throws DaoException the dao exception
     */
    Profile findById(long id) throws DaoException;

    /**
     * Check update availability.
     *
     * @param profile the profile
     * @return availability boolean
     * @throws DaoException the dao exception
     */
    boolean checkUpdateAvailability(Profile profile) throws DaoException;

    /**
     * Find applied profile.
     *
     * @param passportId the passport id
     * @return the profile
     * @throws DaoException the dao exception
     */
    Profile findApplied(String passportId) throws DaoException;

    /**
     * Find applied by last name list.
     *
     * @param lastName     the last name
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list of profiles
     * @throws DaoException the dao exception
     */
    List<Profile> findAppliedByLastName(String lastName, int offset, int recordsCount) throws DaoException;

    /**
     * Find all applied list.
     *
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list of applied profiles
     * @throws DaoException the dao exception
     */
    List<Profile> findAllApplied(int offset, int recordsCount) throws DaoException;

    /**
     * Find all profiles non-applied list.
     *
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list profiles
     * @throws DaoException the dao exception
     */
    List<Profile> findAllProfiles(int offset, int recordsCount) throws DaoException;

    /**
     * Gets records count.
     *
     * @return the records count
     */
    int getRecordsCount();

    /**
     * Gets to profiles apply.
     *
     * @param facultyId          the faculty id
     * @param isFreeForm         the is free form
     * @param isOutOfCompetition the is out of competition
     * @param quota              the quota
     * @return profiles to apply
     * @throws DaoException the dao exception
     */
    List<Profile> getToApply(long facultyId, boolean isFreeForm, boolean isOutOfCompetition, int quota)
            throws DaoException;

    /**
     * Gets profiles with same points.
     *
     * @param facultyId  the faculty id
     * @param isFreeForm the is free form
     * @param points     the points
     * @param quota      the quota
     * @return the with same points
     * @throws DaoException the dao exception
     */
    List<Profile> getWithSamePoints(long facultyId, boolean isFreeForm, int points, int quota) throws DaoException;
}
