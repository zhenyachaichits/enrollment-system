package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.InvalidDataException;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;
import com.epam.finaltask.university.validator.UserValidator;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Zheny Chaichits on 10.02.2016.
 */
public class UserService implements Service {

    private UserService() { }

    public static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }

    public boolean checkAccountData(User user) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            String email = user.getEmail();
            String password = user.getPassword();

            String passwordHash = DataEncrypter.encrypt(password);

            user = dao.findUserToLogIn(email, passwordHash);

            return user != null;

        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide account checking service", e);
        }
    }

    public User authentificateUser(User user) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            String email = user.getEmail();
            String password = user.getPassword();

            String passwordHash = DataEncrypter.encrypt(password);

            user = dao.findUserToLogIn(email, passwordHash);

            return user;

        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide authentication service", e);
        }
    }

    public boolean checkEmailExistence(String email) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.find(email) != null;
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide Email checking service", e);
        }
    }

    public boolean createNewAccount(User user) throws InvalidDataException, ServiceException {
        if (UserValidator.validateUser(user) && !checkEmailExistence(user.getEmail())) {
            Lock lock = new ReentrantLock();

            lock.lock();
            try {
                UserDao dao = DaoFactory.getDaoFactory().getUserDao();
                user.setPassword(DataEncrypter.encrypt(user.getPassword()));
                user = dao.add(user);

                return user != null;

            } catch (DaoFactoryException | DaoException e) {
                throw new ServiceException("Couldn't provide account creation service");
            } finally {
                lock.unlock();
            }
        } else {
            throw new InvalidDataException("Invalid user data. Operation Stopped");
        }
    }

    public User getUserById(long id) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.findUserById(id);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide Email checking service", e);
        }
    }
}
