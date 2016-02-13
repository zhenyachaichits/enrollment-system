package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 05.02.16.
 */
public class UserDaoImpl implements UserDao {

    private static UserDaoImpl instance;
    ConnectionPool connectionPool;

    private UserDaoImpl() throws DaoException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException("Couldn't get instance of connection pool", e);
        }
    }

    private static final int MIN_PARAMETER_INDEX = 1;

    private static final String ID_KEY = "user_id";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_HASH_KEY = "password_hash";
    private static final String ROLE_KEY = "role";

    private static final String GET_ROLE_QUERY = "SELECT * FROM user WHERE email = ? AND password_hash = ?";
    private static final String GET_ALL_USERS_QUERY = "SELECT * FROM user";
    private static final String ADD_USER_QUERY = "INSERT INTO user (email, password_hash) values (?, ?)";
    private static final String ADD_USER_WITH_ROLE_QUERY = "INSERT INTO user (email, password_hash, role) values (?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET email = ? AND password_hash = ? AND role = ?";
    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id = ?";


    public static UserDaoImpl getInstance() throws DaoException {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public User findUserToLogIn(String email, String passwordHash) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ROLE_QUERY);
        ) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordHash);

            statement.setString(1, email);
            statement.setString(2, passwordHash);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(ID_KEY));

                String role = resultSet.getString(ROLE_KEY);
                user.setRole(UserType.valueOf(role));

                return user;
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public User findUserById(long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
        ) {
            User user = new User();
            user.setId(id);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setEmail(resultSet.getString(EMAIL_KEY));

                UserType userType = UserType.valueOf(resultSet.getString(ROLE_KEY));
                user.setRole(userType);

                return user;
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }


    @Override
    public User add(User user) throws DaoException {
        String query;
        if (user.getRole() == null) {
            query = ADD_USER_QUERY;
        } else {
            query = ADD_USER_WITH_ROLE_QUERY;
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            if (user.getRole() != null) {
                statement.setString(3, user.getRole().toString());
            }

            int result = statement.executeUpdate();

            if (result == 0) {
                return user;
            } else {
                return null;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public User find(String email) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY);
        ) {
            User user = new User();

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setEmail(resultSet.getString(EMAIL_KEY));
                user.setPassword(resultSet.getString(PASSWORD_HASH_KEY));

                UserType userType = UserType.valueOf(resultSet.getString(ROLE_KEY));
                user.setRole(userType);

                return user;
            } else {
                return null;
            }


        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public User update(User user) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
        ) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());

            int result = statement.executeUpdate();

            connection.commit();

            if (result == 0) {
                return user;
            } else {
                return null;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public User delete(String domain) throws DaoException {
        return null;
    }

    @Override
    public List<User> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<User> userList = new ArrayList<>();

            User currentUser = new User();

            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (resultSet.next()) {

                currentUser.setEmail(resultSet.getString(EMAIL_KEY));
                currentUser.setPassword(resultSet.getString(PASSWORD_HASH_KEY));

                UserType userType = UserType.valueOf(resultSet.getString(ROLE_KEY));
                currentUser.setRole(userType);

                userList.add(currentUser);
            }

            return userList;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}