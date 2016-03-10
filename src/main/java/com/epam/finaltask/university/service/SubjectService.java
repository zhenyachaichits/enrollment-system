package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;

/**
 * Subject service.
 */
public class SubjectService {

    private SubjectService() { }

    public static class SubjectServiceHolder {
        public static final SubjectService INSTANCE = new SubjectService();
    }

    public static SubjectService getInstance() {
        return SubjectServiceHolder.INSTANCE;
    }

    /**
     * Gets subjects by faculty id.
     *
     * @param facultyId the faculty id
     * @return the subjects by faculty id
     * @throws ServiceException the service exception
     */
    public List<Subject> getSubjectsByFacultyId(long facultyId) throws ServiceException {
        try {
            SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

            return dao.getSubjectsByFacultyId(facultyId);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide subjects finding service", e);
        }
    }

    /**
     * Gets all subjects.
     *
     * @return the all subjects
     * @throws ServiceException the service exception
     */
    public List<Subject> getAllSubjects() throws ServiceException {
        try {
            SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

            return dao.all();
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide subjects finding service", e);
        }
    }

    /**
     * Check subject existence.
     *
     * @param name the name
     * @return true if exists, else false
     * @throws ServiceException the service exception
     */
    public boolean checkSubjectExistence(String name) throws ServiceException {
        try {
            SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

            return dao.find(name) != null;
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide subjects existence checking service", e);
        }
    }

    /**
     * Check update availability.
     *
     * @param subject the subject
     * @return true if available, else false
     * @throws ServiceException the service exception
     */
    public boolean checkUpdateAvailability(Subject subject) throws ServiceException {
        try {
            SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

            return dao.checkUpdateAvailability(subject);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide subjects existence checking service", e);
        }
    }

    public boolean checkDeletionAvailability(long subjectId) throws ServiceException {
        try {
            SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

            return dao.checkDeletionAvailability(subjectId);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide subjects existence checking service", e);
        }
    }
}
