package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.common.ProfileCommon;
import com.epam.finaltask.university.dao.common.UserCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.impl.ProfileDaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.UserDaoBeanFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Sql student dao.
 */
public class SqlStudentDaoImpl implements StudentDao {

    private final ConnectionPool connectionPool;

    private SqlStudentDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class RegisterTransactionDaoHolder {
        public static final SqlStudentDaoImpl INSTANCE = new SqlStudentDaoImpl();
    }

    public static SqlStudentDaoImpl getInstance() {
        return RegisterTransactionDaoHolder.INSTANCE;
    }

    private static final String CHECK_STUDENT_QUERY = "SELECT user.user_id FROM user INNER JOIN profile ON " +
            "user.user_id = profile.user_user_id WHERE user.email = ? AND profile.passport_id = ? AND " +
            "user.status = 'ACTIVE' AND profile.status = 'ACTIVE'";
    private static final String CHECK_UPDATE_AVAILABILITY = "SELECT user.user_id FROM user INNER JOIN profile ON " +
            "user.user_id = profile.user_user_id WHERE user.user_id <> ? AND user.email = ?" +
            "AND profile.passport_id = ? AND user.status = 'ACTIVE' AND profile.status = 'ACTIVE'";
    private static final String FIND_STUDENT_QUERY = "SELECT user.*, profile.* FROM user INNER JOIN profile ON " +
            "user.user_id = profile.user_user_id WHERE user.email = ? AND user.status = 'ACTIVE' " +
            "AND profile.status = 'ACTIVE'";

    /**
     * Checks student existence
     *
     * @param student
     * @return true if student exists, else false
     * @throws DaoException
     */
    @Override
    public boolean checkStudentExistence(Student student) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_STUDENT_QUERY);
        ) {
            statement.setString(1, student.getUser().getEmail());
            statement.setString(2, student.getProfile().getPassportId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Checks student update availability
     *
     * @param student
     * @return true if update available, else false
     * @throws DaoException
     */
    @Override
    public boolean checkUpdateAvailability(Student student) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY);
        ) {
            statement.setLong(1, student.getUser().getId());
            statement.setString(2, student.getUser().getEmail());
            statement.setString(3, student.getProfile().getPassportId());

            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Creates new student: add user and corresponding profile.
     *
     * @param student
     * @return created student or
     * @throws DaoException
     */
    @Override
    public Student add(Student student) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            User user = student.getUser();
            Profile profile = student.getProfile();

            UserCommon userCommon = UserCommon.getInstance();
            user = userCommon.createUser(user, connection);

            if (user != null) {
                profile.setUserId(user.getId());

                ProfileCommon profileCommon = ProfileCommon.getInstance();
                profile = profileCommon.createProfile(profile, connection);
            }

            if (user != null && profile != null) {
                connection.commit();

                student.setUser(user);
                student.setProfile(profile);
                return student;
            } else {
                connection.rollback();
                return null;
            }

        } catch (ConnectionPoolException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                // TODO: 16.02.2016 logger?
            }
            throw new DaoException("Couldn't process operation", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    // TODO: 19.02.2016 logger
                }
                connectionPool.closeConnection(connection);
            }
        }
    }

    /**
     * Searching student by email
     *
     * @param email user email
     * @return user or null, uf user wasn't found
     * @throws DaoException
     */
    @Override
    public Student find(String email) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_QUERY);
        ) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDaoBeanFactory userConstructor = UserDaoBeanFactory.getInstance();
                ProfileDaoBeanFactory profileConstructor = ProfileDaoBeanFactory.getInstance();

                User user = userConstructor.construct(resultSet);
                Profile profile = profileConstructor.construct(resultSet);

                return new Student(user, profile);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Updates student data
     *
     * @param student
     * @return student or null if couldn't update
     * @throws DaoException
     */
    @Override
    public Student update(Student student) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            User user = student.getUser();
            Profile profile = student.getProfile();

            UserCommon userCommon = UserCommon.getInstance();
            user = userCommon.updateUser(user, connection);

            if (user != null) {
                profile.setUserId(user.getId());

                ProfileCommon profileCommon = ProfileCommon.getInstance();
                profile = profileCommon.updateProfile(profile, connection);
            }

            if (user != null && profile != null) {
                connection.commit();

                student.setUser(user);
                student.setProfile(profile);
                ;
                return student;
            } else {
                connection.rollback();
                return null;
            }

        } catch (ConnectionPoolException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                // TODO: 16.02.2016 logger?
            }
            throw new DaoException("Couldn't process operation", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    // TODO: 19.02.2016 logger
                }
                connectionPool.closeConnection(connection);
            }
        }
    }

    @Override
    public Student delete(String domain) throws DaoException {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    /**
     * Delete student by user ID
     *
     * @param userId
     * @return true if student was deleted, else false
     * @throws DaoException
     */
    @Override
    public boolean delete(long userId) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);


            Profile profile = new Profile();
            profile.setUserId(userId);

            UserCommon userCommon = UserCommon.getInstance();
            boolean isUserDeleted = userCommon.deleteUser(userId, connection);
            ProfileCommon profileCommon = ProfileCommon.getInstance();
            boolean isProfileDeleted = profileCommon.deleteProfile(userId, connection);

            if (isUserDeleted && isProfileDeleted) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }

        } catch (ConnectionPoolException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                // TODO: 16.02.2016 logger?
            }
            throw new DaoException("Couldn't process operation", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    // TODO: 19.02.2016 logger
                }
                connectionPool.closeConnection(connection);
            }
        }
    }

    @Override
    public List<Student> all() {
        throw new UnsupportedOperationException("Student Dao doesn't support this operation");
    }
}
