package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;

import java.util.List;

/**
 * User service.
 */
public class UserService {

    private UserService() {
    }

    public static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }

    /**
     * Check account data for authentication.
     *
     * @param user the user
     * @return true if data is valid
     * @throws ServiceException the service exception
     */
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

    /**
     * Authenticate user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
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

    /**
     * Check email existence.
     *
     * @param email the email
     * @return true if exists, else false
     * @throws ServiceException the service exception
     */
    public boolean checkEmailExistence(String email) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.find(email) != null;
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide Email checking service", e);
        }
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     * @throws ServiceException the service exception
     */
    public User getUserById(long id) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.findUserById(id);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide user searching service", e);
        }
    }

    /**
     * Find all users by role list.
     *
     * @param role         the role
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list
     * @throws ServiceException the service exception
     */
    public List<User> findAllUsersByRole(UserType role, int offset, int recordsCount) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.findUsersByRole(role, offset, recordsCount);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide users finding service", e);
        }
    }

    /**
     * Gets current records count.
     *
     * @return the current records count
     * @throws ServiceException the service exception
     */
    public int getCurrentRecordsCount() throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.getRecordsCount();
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide records counting service");
        }
    }

    /**
     * Check update availability.
     *
     * @param user the user
     * @return true if available, else false
     * @throws ServiceException the service exception
     */
    public boolean checkUpdateAvailability(User user) throws ServiceException {
        try {
            UserDao dao = DaoFactory.getDaoFactory().getUserDao();

            return dao.checkUpdateAvailability(user);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide update availability checking service");
        }
    }
}
