package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.validator.FacultyValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * -
 * Locking faculty service.
 */
public class LockingFacultyService {

    private LockingFacultyService() {
    }

    public static class LockingFacultyServiceHolder {
        public static final LockingFacultyService INSTANCE = new LockingFacultyService();
    }

    public static LockingFacultyService getInstance() {
        return LockingFacultyServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    /**
     * Create faculty.
     *
     * @param faculty the faculty
     * @return the faculty
     * @throws ServiceException the service exception
     */
    public Faculty createFaculty(Faculty faculty) throws ServiceException {
        FacultyService service = FacultyService.getInstance();

        if (FacultyValidator.validateFaculty(faculty) && !service.checkFacultyExistence(faculty.getName())) {
            lock.lock();
            try {
                FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

                faculty = dao.add(faculty);

                return faculty;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide faculty creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid faculty data. Operation Stopped");
        }
    }

    /**
     * Update faculty.
     *
     * @param faculty the faculty
     * @return the faculty
     * @throws ServiceException the service exception
     */
    public Faculty updateFaculty(Faculty faculty) throws ServiceException {
        FacultyService service = FacultyService.getInstance();
        if (FacultyValidator.validateFaculty(faculty) && service.checkUpdateAvailability(faculty)) {
            lock.lock();
            try {
                FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

                return dao.update(faculty);
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide faculty updating service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid faculty data. Operation Stopped");
        }
    }

    /**
     * Delete faculty.
     *
     * @param facultyId the faculty id
     * @return true if deleted, else false
     * @throws ServiceException the service exception
     */
    public boolean deleteFaculty(long facultyId) throws ServiceException {
        FacultyService service = FacultyService.getInstance();
        if (service.checkDeletionAvailability(facultyId)) {
            lock.lock();
            try {
                FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

                return dao.delete(facultyId);
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide subject deletion service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Deletion is not available. Operation Stopped");
        }
    }

    /**
     * Update points.
     *
     * @param facultyId the faculty id
     * @param freePoint the free point
     * @param paidPoint the paid point
     * @return true if updated, else false
     * @throws ServiceException the service exception
     */
    public boolean updatePoints(long facultyId, int freePoint, int paidPoint) throws ServiceException {
        lock.lock();
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.updatePoints(facultyId, freePoint, paidPoint);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide subject deletion service");
        } finally {
            lock.unlock();
        }
    }
}
