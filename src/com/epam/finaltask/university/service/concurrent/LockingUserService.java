package com.epam.finaltask.university.service.concurrent;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;
import com.epam.finaltask.university.validator.UserValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Zheny Chaichits on 14.02.2016.
 */
public class LockingUserService {

    private LockingUserService() {
    }

    public static class LockingUserServiceHolder {
        public static final LockingUserService INSTANCE = new LockingUserService();
    }

    public static LockingUserService getInstance() {
        return LockingUserServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    public User createUser(User user) throws ServiceException {
        UserService userService = UserService.getInstance(); // TODO: ask about it

        if (UserValidator.validateUser(user) && !userService.checkEmailExistence(user.getEmail())) {
            lock.lock();
            try {
                UserDao dao = DaoFactory.getDaoFactory().getUserDao();
                user.setPassword(DataEncrypter.encrypt(user.getPassword()));
                user = dao.add(user);

                return user;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide user creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid user data. Operation Stopped");
        }
    }

    public User updateUser(User user) throws ServiceException {
        UserService service = UserService.getInstance();

        if (UserValidator.validateUserForUpdate(user) && service.checkUpdateAvailability(user)) {
            lock.lock();
            try {
                UserDao dao = DaoFactory.getDaoFactory().getUserDao();

                user = dao.update(user);

                return user;
            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide user updating service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid user data. Operation Stopped");
        }
    }

    public boolean deleteUser(long userId) throws ServiceException {
        lock.lock();
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.deleteById(userId);

        } catch (DaoFactoryException | DaoException e) {
            throw new ServiceException("Couldn't provide account deletion service");
        } finally {
            lock.unlock();
        }
    }

}
