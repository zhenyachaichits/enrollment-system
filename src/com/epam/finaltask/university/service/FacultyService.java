package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 16.02.2016.
 */
public class FacultyService {

    private FacultyService() { }

    public static class FacultyServiceHolder {
        public static final FacultyService INSTANCE = new FacultyService();
    }

    public static FacultyService getInstance() {
        return FacultyServiceHolder.INSTANCE;
    }

    public List<Faculty> getAllFaculties() throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.all();

        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    public boolean checkFacultyExistence(String name) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.find(name) != null;
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    public boolean checkUpdateAvailability(Faculty faculty) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.checkUpdateAvailability(faculty);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }

    public Faculty findFacultyById(long facultyId) throws ServiceException {
        try {
            FacultyDao dao = DaoFactory.getDaoFactory().getFacultyDao();

            return dao.find(facultyId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide faculties searching service", e);
        }
    }
}
