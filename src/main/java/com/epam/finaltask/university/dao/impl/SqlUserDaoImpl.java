package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.common.UserCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.impl.UserDaoBeanFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Sql user dao.
 */
public class SqlUserDaoImpl implements UserDao {

    private final ConnectionPool connectionPool;
    private int recordsCount;

    private SqlUserDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class UserDaoHolder {
        public static final SqlUserDaoImpl INSTANCE = new SqlUserDaoImpl();
    }

    public static SqlUserDaoImpl getInstance() {
        return UserDaoHolder.INSTANCE;
    }

    private static final String GET_ROLE_QUERY = "SELECT * FROM user WHERE email = ? AND password_hash = ? " +
            "AND status = 'ACTIVE'";
    private static final String GET_ALL_USERS_QUERY = "SELECT * FROM user WHERE status = 'ACTIVE'";

    private static final String FIND_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ? AND status = 'ACTIVE'";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id = ? AND status = 'ACTIVE'";
    private static final String FIND_USERS_BY_ROLE_QUERY = "SELECT SQL_CALC_FOUND_ROWS * FROM user WHERE role = ? " +
            "AND status = 'ACTIVE' LIMIT ?, ?";
    private static final String CHECK_UPDATE_AVAILABILITY_QUERY = "SELECT * FROM user " +
            "WHERE user_id <> ? AND email = ? AND status = 'ACTIVE'";
    private static final String GET_COUNT_QUERY = "SELECT FOUND_ROWS()";

    /**
     * Search user for authentication
     *
     * @param email        user email
     * @param passwordHash user password
     * @return user or null, if couldn't find
     * @throws DaoException
     */
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
                UserDaoBeanFactory constructor = UserDaoBeanFactory.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Search user by ID
     *
     * @param id user ID
     * @return found user or null, if couldn't find
     * @throws DaoException
     */
    @Override
    public User findUserById(long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
        ) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserDaoBeanFactory constructor = UserDaoBeanFactory.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Search users by role
     *
     * @param role         user role
     * @param offset       offset
     * @param recordsCount records count
     * @return list of found users
     * @throws DaoException
     */
    @Override
    public List<User> findUsersByRole(UserType role, int offset, int recordsCount) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_ROLE_QUERY);
        ) {
            statement.setString(1, role.toString());
            statement.setInt(2, offset);
            statement.setInt(3, recordsCount);

            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();

            while (resultSet.next()) {
                UserDaoBeanFactory factory = UserDaoBeanFactory.getInstance();
                users.add(factory.construct(resultSet));
            }

            resultSet = statement.executeQuery(GET_COUNT_QUERY);
            if (resultSet.next()) {
                this.recordsCount = resultSet.getInt(1);
            }

            return users;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Checks user's update availability
     *
     * @param user
     * @return true if update is available, else false
     * @throws DaoException
     */
    @Override
    public boolean checkUpdateAvailability(User user) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY_QUERY);
        ) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getEmail());

            ResultSet resultSet = statement.executeQuery();

            return !resultSet.next();
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets records count
     *
     * @return records count
     */
    @Override
    public int getRecordsCount() {
        return recordsCount;
    }

    /**
     * Delete user by ID
     *
     * @param id user ID
     * @return true if deleted, else false
     * @throws DaoException
     */
    @Override
    public boolean deleteById(long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            UserCommon service = UserCommon.getInstance();

            return service.deleteUser(id, connection);
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Creates new user
     *
     * @param user
     * @return created user
     * @throws DaoException
     */
    @Override
    public User add(User user) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            UserCommon service = UserCommon.getInstance();
            user = service.createUser(user, connection);

            return user;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Search user by Email
     *
     * @param email user email
     * @return found user or null
     * @throws DaoException
     */
    @Override
    public User find(String email) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_QUERY);
        ) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UserDaoBeanFactory constructor = UserDaoBeanFactory.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Update user data
     *
     * @param user
     * @return updated user or null if couldn't update
     * @throws DaoException
     */
    @Override
    public User update(User user) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            UserCommon service = UserCommon.getInstance();
            user = service.updateUser(user, connection);

            return user;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public User delete(String email) throws DaoException {
        throw new UnsupportedOperationException("Deleting user by email unsupported");
    }

    /**
     * Gets all users
     *
     * @return list of users
     * @throws DaoException
     */
    @Override
    public List<User> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<User> userList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            while (resultSet.next()) {
                UserDaoBeanFactory constructor = UserDaoBeanFactory.getInstance();

                userList.add(constructor.construct(resultSet));
            }

            return userList;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}
