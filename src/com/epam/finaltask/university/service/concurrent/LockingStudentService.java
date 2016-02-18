package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Student;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.Service;
import com.epam.finaltask.university.service.exception.InvalidDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.impl.StudentService;
import com.epam.finaltask.university.service.util.DataEncrypter;
import com.epam.finaltask.university.validator.StudentValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class LockingStudentService implements Service {
    private LockingStudentService() {
    }

    public static class LockingStudentServiceHolder {
        public static final LockingStudentService INSTANCE = new LockingStudentService();
    }

    public static LockingStudentService getInstance() {
        return LockingStudentServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    public Student createNewAccount(Student student) throws InvalidDataException, ServiceException {
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
            throw new InvalidDataException("Invalid student data. Operation Stopped");
        }
    }
}
