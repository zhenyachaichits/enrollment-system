package com.epam.finaltask.university.service.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.Service;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;

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

    public User authenticateUser(User user) throws ServiceException {
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

    public User getUserById(long id) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.findUserById(id);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide Email checking service", e);
        }
    }
}
