package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.User;

import java.sql.*;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class UserDaoService {

    private UserDaoService() { }

    public static class UserDaoServiceHolder {
        public static final UserDaoService INSTANCE = new UserDaoService();
    }

    public static UserDaoService getInstance() {
        return UserDaoServiceHolder.INSTANCE;
    }

    private static final String ADD_USER_QUERY = "INSERT INTO user (email, password_hash) values (?, ?)";
    private static final String ADD_USER_WITH_ROLE_QUERY = "INSERT INTO user (email, password_hash, role) values (?, ?, ?)";

    public User createUser(User user, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        try {
            String query;

            if (user.getRole() == null) {
                query = ADD_USER_QUERY;
            } else {
                query = ADD_USER_WITH_ROLE_QUERY;
            }

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

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
                connection.commit();
                return user;
            } else {
                connection.rollback();
                return null;
            }

        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

}
