package com.epam.finaltask.university.service.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.Service;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class ProfileService implements Service {

    private ProfileService() { }

    public static class ProfileServiceHolder {
        public static final ProfileService INSTANCE = new ProfileService();
    }

    public static ProfileService getInstance() {
        return ProfileServiceHolder.INSTANCE;
    }

    public boolean checkPassportIdExistence(String passportId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.find(passportId) != null;
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide Email checking service", e);
        }
    }

    public Profile findProfileByPassportId(String passportId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.find(passportId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    public Profile findProfileById(long profileId) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findById(profileId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    public List<Profile> findProfilesByLastName(String lastName) throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.findByLastName(lastName);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide profile finding service", e);
        }
    }

    public List<Profile> getAllProfiles() throws ServiceException {
        try {
            ProfileDao dao = DaoFactory.getDaoFactory().getProfileDao();

            return dao.all();
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide all profiles finding service", e);
        }
    }
}
