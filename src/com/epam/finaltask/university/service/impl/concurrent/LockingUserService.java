package com.epam.finaltask.university.service.impl.concurrent;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.Service;
import com.epam.finaltask.university.service.impl.UserService;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;
import com.epam.finaltask.university.validator.UserValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Zheny Chaichits on 14.02.2016.
 */
public class LockingUserService implements Service {

    private LockingUserService() {
    }

    public static class LockingUserServiceHolder {
        public static final LockingUserService INSTANCE = new LockingUserService();
    }

    public static LockingUserService getInstance() {
        return LockingUserServiceHolder.INSTANCE;
    }

    private static final Lock lock = new ReentrantLock();

    public User createNewAccount(User user) throws InvalidServiceDataException, ServiceException {
        UserService userService = UserService.getInstance(); // TODO: ask about it

        if (UserValidator.validateUser(user) && !userService.checkEmailExistence(user.getEmail())) {
            lock.lock();
            try {
                UserDao dao = DaoFactory.getDaoFactory().getUserDao();
                user.setPassword(DataEncrypter.encrypt(user.getPassword()));
                user = dao.add(user);

                return user;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide account creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidServiceDataException("Invalid user data. Operation Stopped");
        }
    }
}
