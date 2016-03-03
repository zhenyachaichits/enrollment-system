package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.validator.ProfileValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Locking profile service.
 */
public class LockingProfileService {
    private LockingProfileService() {
    }

    public static class LockingProfileServiceHolder {
        public static final LockingProfileService INSTANCE = new LockingProfileService();
    }

    public static LockingProfileService getInstance() {
        return LockingProfileServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    /**
     * Create new profile.
     *
     * @param profile the profile
     * @return the profile
     * @throws ServiceException the service exception
     */
    public Profile createNewProfile(Profile profile) throws ServiceException {
        ProfileService profileService = ProfileService.getInstance();

        if (ProfileValidator.validateProfile(profile) && !profileService.checkPassportIdExistence(profile.getPassportId())) {
            lock.lock();
            try {
                ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

                profile = dao.add(profile);

                return profile;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide profile creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid profile data. Operation Stopped");
        }
    }

    /**
     * Update profile.
     *
     * @param profile the profile
     * @return the profile
     * @throws ServiceException the service exception
     */
    public Profile updateProfile(Profile profile) throws ServiceException {
        ProfileService profileService = ProfileService.getInstance();

        if (ProfileValidator.validateProfile(profile) && profileService.checkUpdateAvailability(profile)) {
            lock.lock();
            try {
                ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

                profile = dao.update(profile);

                return profile;
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide profile creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid profile data. Operation Stopped");
        }
    }
}
