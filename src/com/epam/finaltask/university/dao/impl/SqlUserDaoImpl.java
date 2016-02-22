package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.common.UserDaoService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 05.02.16.
 */
public class SqlUserDaoImpl implements UserDao {

    private final ConnectionPool connectionPool;

    private SqlUserDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class UserDaoHolder {
        public static final SqlUserDaoImpl INSTANCE = new SqlUserDaoImpl();
    }

    public static SqlUserDaoImpl getInstance() {
        return UserDaoHolder.INSTANCE;
    }

    private static final int MIN_PARAMETER_INDEX = 1;

    private static final String ID_KEY = "user_id";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_HASH_KEY = "password_hash";
    private static final String ROLE_KEY = "role";

    private static final String GET_ROLE_QUERY = "SELECT * FROM user WHERE email = ? AND password_hash = ? AND status = 'ACTIVE'";
    private static final String GET_ALL_USERS_QUERY = "SELECT * FROM user WHERE status = 'ACTIVE'";

    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ? AND status = 'ACTIVE'";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id = ? AND status = 'ACTIVE'";

    @Override
    public User findUserToLogIn(String email, String passwordHash) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_ROLE_QUERY);
        ) {
            statement.setString(1, email);
            statement.setString(2, passwordHash);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDaoService service = UserDaoService.getInstance();

                return service.compileUser(resultSet);
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
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserDaoService service = UserDaoService.getInstance();

                return service.compileUser(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }


    @Override
    public User add(User user) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            UserDaoService service = UserDaoService.getInstance();
            user = service.createUser(user, connection);

            return user;
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
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                UserDaoService service = UserDaoService.getInstance();

                return service.compileUser(resultSet);
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
        ) {
            UserDaoService service = UserDaoService.getInstance();
            user = service.updateUser(user, connection);

            return user;
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

            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (resultSet.next()) {
                UserDaoService service = UserDaoService.getInstance();

                userList.add(service.compileUser(resultSet));
            }

            return userList;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}