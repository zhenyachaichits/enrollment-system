package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;

import java.sql.*;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class UserDaoService {

    private UserDaoService() {
    }

    public static class UserDaoServiceHolder {
        public static final UserDaoService INSTANCE = new UserDaoService();
    }

    public static UserDaoService getInstance() {
        return UserDaoServiceHolder.INSTANCE;
    }

    private static final String ID_KEY = "user_id";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_HASH_KEY = "password_hash";
    private static final String ROLE_KEY = "role";

    private static final String ADD_USER_QUERY = "INSERT INTO user (email, password_hash) values (?, ?)";
    private static final String ADD_USER_WITH_ROLE_QUERY = "INSERT INTO user (email, password_hash, role) values (?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET email = ? AND password_hash = ? AND role = ?";

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
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            if (user.getRole() != null) {
                statement.setString(3, user.getRole().toString());
            }

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

    public User compileUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong(ID_KEY));
        user.setEmail(resultSet.getString(EMAIL_KEY));
        user.setPassword(resultSet.getString(PASSWORD_HASH_KEY));

        UserType userType = UserType.valueOf(resultSet.getString(ROLE_KEY));
        user.setRole(userType);

        return user;
    }

}
