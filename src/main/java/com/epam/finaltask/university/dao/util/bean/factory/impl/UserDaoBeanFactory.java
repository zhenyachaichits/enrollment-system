package com.epam.finaltask.university.dao.util.bean.factory.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User dao bean factory.
 */
public class UserDaoBeanFactory implements DaoBeanFactory<User> {

    private UserDaoBeanFactory() {
    }

    public static class UserDaoConstructorHolder {
        public static final UserDaoBeanFactory INSTANCE = new UserDaoBeanFactory();
    }

    public static UserDaoBeanFactory getInstance() {
        return UserDaoConstructorHolder.INSTANCE;
    }


    private static final String ID_KEY = "user_id";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_HASH_KEY = "password_hash";
    private static final String ROLE_KEY = "role";

    /**
     * Constructs User bean from ResultSet
     *
     * @param resultSet the result set
     * @return
     * @throws SQLException
     */
    @Override
    public User construct(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong(ID_KEY));
        user.setEmail(resultSet.getString(EMAIL_KEY));
        user.setPassword(resultSet.getString(PASSWORD_HASH_KEY));

        UserType userType = UserType.valueOf(resultSet.getString(ROLE_KEY));
        user.setRole(userType);

        return user;
    }

}
