package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;


/**
 * Profile service.
 */
public class ProfileService {

    private ProfileService() { }

    public static class ProfileServiceHolder {
        public static final ProfileService INSTANCE = new ProfileService();
    }

    public static ProfileService getInstance() {
        return ProfileServiceHolder.INSTANCE;
    }

    /**
     * Check profile with such passport id existence.
     *
     * @param passportId the passport id
     * @return true if exists, else false
     * @throws ServiceException the service exception
     */
    public boolean checkPassportIdExistence(String passportId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.find(passportId) != null;
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide Email checking service", e);
        }
    }

    /**
     * Find profile by passport id.
     *
     * @param passportId the passport id
     * @return the profile
     * @throws ServiceException the service exception
     */
    public Profile findProfileByPassportId(String passportId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.find(passportId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    /**
     * Find applied profile by passport id.
     *
     * @param passportId the passport id
     * @return the profile
     * @throws ServiceException the service exception
     */
    public Profile findAppliedProfileByPassportId(String passportId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findApplied(passportId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    /**
     * Find profile by id.
     *
     * @param profileId the profile id
     * @return the profile
     * @throws ServiceException the service exception
     */
    public Profile findProfileById(long profileId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findById(profileId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    /**
     * Find profiles by last name list.
     *
     * @param lastName     the last name
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list
     * @throws ServiceException the service exception
     */
    public List<Profile> findProfilesByLastName(String lastName, int offset, int recordsCount) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findProfilesByLastName(lastName, offset, recordsCount);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    /**
     * Find applied profiles by last name list.
     *
     * @param lastName     the last name
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list
     * @throws ServiceException the service exception
     */
    public List<Profile> findAppliedProfilesByLastName(String lastName, int offset, int recordsCount)
            throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findAppliedByLastName(lastName, offset, recordsCount);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    public List<Profile> findEnrolledToFaculty(long facultyId, int offset, int recordsCount) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findAllEnrolled(facultyId, offset, recordsCount);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    /**
     * Gets all profiles.
     *
     * @return the all profiles
     * @throws ServiceException the service exception
     */
    public List<Profile> getAllProfiles() throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.all();
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide all profiles finding service", e);
        }
    }

    /**
     * Gets all profiles.
     *
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the all profiles
     * @throws ServiceException the service exception
     */
    public List<Profile> getAllProfiles(int offset, int recordsCount) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findAllProfiles(offset, recordsCount);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide all profiles finding service", e);
        }
    }

    /**
     * Gets all applied profiles.
     *
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the all applied
     * @throws ServiceException the service exception
     */
    public List<Profile> getAllApplied(int offset, int recordsCount) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findAllApplied(offset, recordsCount);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide all profiles finding service", e);
        }
    }

    /**
     * Check update availability.
     *
     * @param student the student
     * @return true if update available, else false
     * @throws ServiceException the service exception
     */
    public boolean checkUpdateAvailability(Profile student) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.checkUpdateAvailability(student);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide student existence checking service");
        }
    }

    /**
     * Gets current records count.
     *
     * @return the current records count
     * @throws ServiceException the service exception
     */
    public int getCurrentRecordsCount() throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.getRecordsCount();
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide records counting service");
        }
    }

    /**
     * Gets profiles to apply.
     *
     * @param facultyId          the faculty id
     * @param isFreeForm         the is free form
     * @param isOutOfCompetition the is out of competition
     * @param quota              the quota
     * @return the list of profiles to apply
     * @throws ServiceException the service exception
     */
    public List<Profile> getProfilesToApply(long facultyId, boolean isFreeForm, boolean isOutOfCompetition, int quota)
            throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.getToApply(facultyId, isFreeForm, isOutOfCompetition, quota);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide records counting service");
        }
    }

    /**
     * Gets profiles with same points.
     *
     * @param facultyId  the faculty id
     * @param isFreeForm the is free form
     * @param points     the points
     * @param quota      the quota
     * @return the list of profiles with same points
     * @throws ServiceException the service exception
     */
    public List<Profile> getWithSamePoints(long facultyId, boolean isFreeForm, int points)
            throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.getWithSamePoints(facultyId, isFreeForm, points);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide records counting service");
        }
    }

    /**
     * Gets profile terms id.
     *
     * @param profileId the profile id
     * @return the profile terms id
     * @throws ServiceException the service exception
     */
    public long getProfileTermsId(long profileId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.getProfileTermsId(profileId);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide terms finding service");
        }
    }
}
