package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;

import java.util.List;

/**
 * Created by Zheny Chaichits on 10.02.2016.
 */
public class UserService {

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
            throw new ServiceException("Couldn't provide user searching service", e);
        }
    }

    public List<User> findAllUsersByRole(UserType role, int offset, int recordsCount) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.findUsersByRole(role, offset, recordsCount);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide users finding service", e);
        }
    }

    public int getCurrentRecordsCount() throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.getRecordsCount();
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide records counting service");
        }
    }
}
