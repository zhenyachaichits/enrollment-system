package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.ApplicationDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.ApplicationService;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.validator.ApplicationValidator;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class LockingApplicationService {

    private LockingApplicationService() {
    }

    public static class LockingApplicationServiceHolder {
        public static final LockingApplicationService INSTANCE = new LockingApplicationService();
    }

    public static LockingApplicationService getInstance() {
        return LockingApplicationServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    private static final boolean OUT_COMPETITION = true;

    public Application createNewApplication(Application application) throws ServiceException {
        ApplicationService applicationService = ApplicationService.getInstance();

        if (ApplicationValidator.validateApplication(application) &&
                !applicationService.checkProfileIdExistence(application.getProfileId())) {
            lock.lock();
            try {
                ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

                application = dao.add(application);

                return application;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide profile creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid profile data. Operation Stopped");
        }
    }

    public boolean deleteApplicationByProfileId(long profileId) throws ServiceException {
        lock.lock();
        try {
            ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

            return dao.delete(profileId) != null;
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide application deleting service", e);
        } finally {
            lock.unlock();
        }
    }

    public boolean confirmApplication(long profileId) throws ServiceException {
        lock.lock();
        try {
            ApplicationDao dao = DaoFactory.getDaoFactory().getApplicationDao();

            return dao.confirmApplication(profileId);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide application finding service", e);
        } finally {
            lock.unlock();
        }
    }

    public boolean confirmFacultyApplications(long facultyId, boolean isFreeForm, int quota) throws ServiceException {
        lock.lock();
        try {
            ProfileService profileService = ProfileService.getInstance();

            List<Profile> profilesToApply = profileService.getProfilesToApply(facultyId, isFreeForm,
                    OUT_COMPETITION, quota);
            if (profilesToApply.size() < quota) {
                List<Profile> profiles = profileService.getProfilesToApply(facultyId, isFreeForm,
                        !OUT_COMPETITION, quota);
                profilesToApply.addAll(profiles);

                if (profilesToApply.size() != 0) {
                    Profile last = profilesToApply.get(profiles.size());
                    int points = last.getPoints();
                    List<Profile> addition = profileService.getWithSamePoints(facultyId, isFreeForm, points, quota);

                    // TODO: 02.03.2016 continue...
                }
            }

            for (Profile profile : profilesToApply) {
                boolean isConfirmed = confirmApplication(profile.getId());
                if (!isConfirmed) {
                    return false;
                }
            }

            return true;
        } finally {
            lock.unlock();
        }
    }
}
