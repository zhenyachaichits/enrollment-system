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
 * Created by Zheny Chaichits on 01.03.2016.
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
}
