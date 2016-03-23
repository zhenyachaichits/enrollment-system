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
 * Locking user service.
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

    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    public User createUser(User user) throws ServiceException {
        UserService userService = UserService.getInstance();

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

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    public User updateUser(User user) throws ServiceException {
        UserService service = UserService.getInstance();

        if (UserValidator.validateUserForUpdate(user) && service.checkUpdateAvailability(user)) {
            lock.lock();
            try {
                UserDao dao = DaoFactory.getDaoFactory().getUserDao();

                if (!"".equals(user.getPassword())) {
                    String passwordHash = DataEncrypter.encrypt(user.getPassword());
                    user.setPassword(passwordHash);
                }

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

    /**
     * Delete user.
     *
     * @param userId the user id
     * @return true if deleted, else false
     * @throws ServiceException the service exception
     */
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
