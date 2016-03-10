package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.SubjectService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.validator.SubjectValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Locking subject service.
 */
public class LockingSubjectService {

    private LockingSubjectService() {
    }

    public static class LockingSubjectServiceHolder {
        public static final LockingSubjectService INSTANCE = new LockingSubjectService();
    }

    public static LockingSubjectService getInstance() {
        return LockingSubjectServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    /**
     * Create subject.
     *
     * @param subject the subject
     * @return the subject
     * @throws ServiceException the service exception
     */
    public Subject createSubject(Subject subject) throws ServiceException {
        SubjectService subjectService = SubjectService.getInstance();
        if(SubjectValidator.validateSubject(subject) && !subjectService.checkSubjectExistence(subject.getName())) {
            lock.lock();
            try {
                SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

                return dao.add(subject);
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide subject updating service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid subject data. Operation Stopped");
        }
    }

    /**
     * Update subject.
     *
     * @param subject the subject
     * @return the subject
     * @throws ServiceException the service exception
     */
    public Subject updateSubject(Subject subject) throws ServiceException {
        SubjectService subjectService = SubjectService.getInstance();
        if(SubjectValidator.validateSubject(subject) && subjectService.checkUpdateAvailability(subject)) {
            lock.lock();
            try {
                SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

                return dao.update(subject);
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide subject updating service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid subject data. Operation Stopped");
        }
    }

    /**
     * Delete subject boolean.
     *
     * @param subjectId the subject id
     * @return true if deleted, else false
     * @throws ServiceException the service exception
     */
    public boolean deleteSubject(long subjectId) throws ServiceException {
        SubjectService subjectService = SubjectService.getInstance();
        if(subjectService.checkDeletionAvailability(subjectId)) {
            lock.lock();
            try {
                SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

                return dao.delete(subjectId);

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide subject deletion service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Deletion is not available. Operation Stopped");
        }
    }
}
