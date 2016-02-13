package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.exception.DaoException;

/**
 * Created by Zheny Chaichits on 02.02.16.
 */
public interface UserDao extends EntityDao<String, User> {

    User findUserToLogIn(String email, String passwordHash) throws DaoException;
    User findUserById(long id) throws DaoException;

}
