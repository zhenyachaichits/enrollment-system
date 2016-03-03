package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;


/**
 * The interface User dao.
 */
public interface UserDao extends EntityDao<String, User> {

    /**
     * Find user to log in.
     *
     * @param email        the email
     * @param passwordHash the password hash
     * @return the user
     * @throws DaoException the dao exception
     */
    User findUserToLogIn(String email, String passwordHash) throws DaoException;

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     * @throws DaoException the dao exception
     */
    User findUserById(long id) throws DaoException;

    /**
     * Find users by role.
     *
     * @param role         the role
     * @param offset       the offset
     * @param recordsCount the records count
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findUsersByRole(UserType role, int offset, int recordsCount) throws DaoException;

    /**
     * Check update availability.
     *
     * @param user the user
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkUpdateAvailability(User user) throws DaoException;

    /**
     * Gets records count.
     *
     * @return the records count
     */
    int getRecordsCount();

    /**
     * Delete by id user.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteById(long id) throws DaoException;
}
