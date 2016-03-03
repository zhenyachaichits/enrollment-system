package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.StudentService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;
import com.epam.finaltask.university.validator.StudentValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Locking student service.
 */
public class LockingStudentService {
    private LockingStudentService() {
    }

    public static class LockingStudentServiceHolder {
        public static final LockingStudentService INSTANCE = new LockingStudentService();
    }

    public static LockingStudentService getInstance() {
        return LockingStudentServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    /**
     * Create new account student.
     *
     * @param student the student
     * @return the student
     * @throws ServiceException the service exception
     */
    public Student createNewAccount(Student student) throws ServiceException {
        StudentService studentService = StudentService.getInstance();

        if (StudentValidator.validateStudent(student) && !studentService.checkStudentExistence(student)) {
            lock.lock();
            try {
                StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();
                String password = student.getUser().getPassword();
                student.getUser().setPassword(DataEncrypter.encrypt(password));
                student = dao.add(student);

                return student;
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide account creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid student data. Operation Stopped");
        }
    }

    /**
     * Update student profile.
     *
     * @param student the student
     * @return the student
     * @throws ServiceException the service exception
     */
    public Student updateStudentProfile(Student student) throws ServiceException {
        StudentService studentService = StudentService.getInstance();

        if (StudentValidator.validateStudentForUpdate(student) && studentService.checkUpdateAvailability(student)) {
            lock.lock();
            try {
                StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();
                String password = student.getUser().getPassword();
                if (!"".equals(password)) {
                    student.getUser().setPassword(DataEncrypter.encrypt(password));
                }
                student = dao.update(student);

                return student;
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide account creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid student data. Operation Stopped");
        }
    }

    /**
     * Delete account.
     *
     * @param userId the user id
     * @return true if deleted, else false
     * @throws ServiceException the service exception
     */
    public boolean deleteAccount(long userId) throws ServiceException {
        lock.lock();
        try {
            StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();

            return dao.delete(userId);

        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide account deletion service");
        } finally {
            lock.unlock();
        }
    }
}
