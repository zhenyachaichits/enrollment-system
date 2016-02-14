package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.Service;
import com.epam.finaltask.university.service.exception.InvalidDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.validator.ProfileValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Zheny Chaichits on 14.02.2016.
 */
public class LockingProfileService implements Service {
    private LockingProfileService() {
    }

    public static class LockingProfileServiceHolder {
        public static final LockingProfileService INSTANCE = new LockingProfileService();
    }

    public static LockingProfileService getInstance() {
        return LockingProfileServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    public boolean createNewProfile(Profile profile) throws ServiceException, InvalidDataException {
        ProfileService profileService = ProfileService.getInstance();

        if (ProfileValidator.validateProfile(profile) && !profileService.checkPassportIdExistence(profile.getPassportId())) {
            lock.lock();
            try {
                ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

                profile = dao.add(profile);

                return profile != null;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide profile creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidDataException("Invalid profile data. Operation Stopped");
        }
    }
}
