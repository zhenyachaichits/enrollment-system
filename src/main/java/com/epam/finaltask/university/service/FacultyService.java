package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;


/**
 * Faculty service.
 */
public class FacultyService {

    private FacultyService() { }

    public static class FacultyServiceHolder {
        public static final FacultyService INSTANCE = new FacultyService();
    }

    public static FacultyService getInstance() {
        return FacultyServiceHolder.INSTANCE;
    }

    /**
     * Gets all faculties.
     *
     * @return the all faculties
     * @throws ServiceException the service exception
     */
    public List<Faculty> getAllFaculties() throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.all();

        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    /**
     * Check faculty existence.
     *
     * @param name the name
     * @return true if exists, else false
     * @throws ServiceException the service exception
     */
    public boolean checkFacultyExistence(String name) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.find(name) != null;
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    /**
     * Check update availability.
     *
     * @param faculty the faculty
     * @return true if available, else false
     * @throws ServiceException the service exception
     */
    public boolean checkUpdateAvailability(Faculty faculty) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.checkUpdateAvailability(faculty);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    /**
     * Find faculty by id.
     *
     * @param facultyId the faculty id
     * @return the faculty
     * @throws ServiceException the service exception
     */
    public Faculty findFacultyById(long facultyId) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.find(facultyId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    public boolean checkDeletionAvailability(long facultyId) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.checkDeletionAvailability(facultyId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide deletion availability checking service", e);
        }
    }
}
