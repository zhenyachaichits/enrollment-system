package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.User;

import java.sql.*;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class UserCommon {

    private UserCommon() {
    }

    public static class UserCommonHolder {
        public static final UserCommon INSTANCE = new UserCommon();
    }

    public static UserCommon getInstance() {
        return UserCommonHolder.INSTANCE;
    }

    private static final int MIN_PARAMETER_INDEX = 1;

    private static final String ADD_USER_QUERY = "INSERT INTO user (email, password_hash) values (?, ?)";
    private static final String ADD_USER_WITH_ROLE_QUERY = "INSERT INTO user (email, password_hash, role) values (?, ?, ?)";

    private static final String UPDATE_USER_QUERY = "UPDATE user SET email = ?, role = ? WHERE user_id = ?";
    private static final String UPDATE_USER_AND_PASSWORD_QUERY = "UPDATE user SET email = ?, password_hash = ?," +
            "role = ? WHERE user_id = ?";

    public User createUser(User user, Connection connection) throws SQLException {
        String query;

        if (user.getRole() == null) {
            query = ADD_USER_QUERY;
        } else {
            query = ADD_USER_WITH_ROLE_QUERY;
        }

        try (
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            if (user.getRole() != null) {
                statement.setString(3, user.getRole().toString());
            }

            int result = statement.executeUpdate();

            if (result != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
                return user;
            } else {
                return null;
            }

        }
    }

    public User updateUser(User user, Connection connection) throws SQLException {
        String query;
        String password = user.getPassword();
        if ("".equals(password)) {
            query = UPDATE_USER_QUERY;
        } else {
            query = UPDATE_USER_AND_PASSWORD_QUERY;
        }
        try (
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            int i = 1;
            statement.setString(i++, user.getEmail());
            if (!"".equals(password)) {
                statement.setString(i++, user.getPassword());
            }
            statement.setString(i++, user.getRole().toString());
            statement.setLong(i, user.getId());

            int result = statement.executeUpdate();

            if (result != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();;
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
                return user;
            } else {
                return null;
            }
        }
    }
}
