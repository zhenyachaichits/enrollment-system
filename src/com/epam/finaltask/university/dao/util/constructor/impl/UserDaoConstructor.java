package com.epam.finaltask.university.dao.util.constructor.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.util.constructor.DaoConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class UserDaoConstructor implements DaoConstructor<User> {

    private UserDaoConstructor() {
    }

    public static class UserDaoConstructorHolder {
        public static final UserDaoConstructor INSTANCE = new UserDaoConstructor();
    }

    public static UserDaoConstructor getInstance() {
        return UserDaoConstructorHolder.INSTANCE;
    }


    private static final String ID_KEY = "user_id";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_HASH_KEY = "password_hash";
    private static final String ROLE_KEY = "role";

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
