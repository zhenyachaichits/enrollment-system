package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;


/**
 * Student service.
 */
public class StudentService {

    private StudentService() { }

    public static class StudentServiceHolder {
        public static final StudentService INSTANCE = new StudentService();
    }

    public static StudentService getInstance() {
        return StudentServiceHolder.INSTANCE;
    }

    /**
     * Check student existence.
     *
     * @param student the student
     * @return true if exists, else false
     * @throws ServiceException the service exception
     */
    public boolean checkStudentExistence(Student student) throws ServiceException {
        try {
            StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();

            return dao.checkStudentExistence(student);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide student existence checking service");
        }
    }

    /**
     * Check update availability.
     *
     * @param student the student
     * @return true if available, else false
     * @throws ServiceException the service exception
     */
    public boolean checkUpdateAvailability(Student student) throws ServiceException {
        try {
            StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();

            return dao.checkUpdateAvailability(student);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide student existence checking service");
        }
    }

    /**
     * Gets student by email.
     *
     * @param email the email
     * @return the student by email
     * @throws ServiceException the service exception
     */
    public Student getStudentByEmail(String email) throws ServiceException {
        try {
            StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();

            return  dao.find(email);
        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide searching student by Email service");
        }
    }

}
