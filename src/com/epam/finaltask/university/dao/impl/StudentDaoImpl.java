package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.common.ProfileDaoService;
import com.epam.finaltask.university.dao.common.UserDaoService;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class StudentDaoImpl implements StudentDao {

    private final ConnectionPool connectionPool;

    private StudentDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class RegisterTransactionDaoHolder {
        public static final StudentDaoImpl INSTANCE = new StudentDaoImpl();
    }

    public static StudentDaoImpl getInstance() {
        return RegisterTransactionDaoHolder.INSTANCE;
    }

    private static final String CHECK_STUDENT_QUERY = "SELECT user.user_id FROM user INNER JOIN profile ON " +
            "user.user_id = profile.user_user_id WHERE user.email = ? AND profile.passport_id = ? AND " +
            "user.status = 'ACTIVE' AND profile.status = 'ACTIVE'";
    private static final String FIND_STUDENT_QUERY = "select user.*, profile.* from user inner join profile on " +
            "user.user_id = profile.user_user_id where user.email = ? AND user.status = 'ACTIVE' " +
            "AND profile.status = 'ACTIVE'";

    @Override
    public boolean checkStudentExistence(Student student) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_STUDENT_QUERY);
        ) {
            statement.setString(1, student.getUser().getEmail());
            statement.setString(2, student.getProfile().getPassportId());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Student add(Student student) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            User user = student.getUser();
            Profile profile = student.getProfile();

            UserDaoService userDaoService = UserDaoService.getInstance();
            user = userDaoService.createUser(user, connection);

            if (user != null) {
                profile.setUserId(user.getId());

                ProfileDaoService profileDaoService = ProfileDaoService.getInstance();
                profile = profileDaoService.createProfile(profile, connection);
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

    @Override
    public Student find(String email) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_STUDENT_QUERY);
        ) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserDaoService userService = UserDaoService.getInstance();
                ProfileDaoService profileService = ProfileDaoService.getInstance();

                User user = userService.compileUser(resultSet);
                Profile profile = profileService.compileProfile(resultSet);

                return new Student(user, profile);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Student update(Student student) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            User user = student.getUser();
            Profile profile = student.getProfile();

            UserDaoService userDaoService = UserDaoService.getInstance();
            user = userDaoService.updateUser(user, connection);

            if (user != null) {
                profile.setUserId(user.getId());

                ProfileDaoService profileDaoService = ProfileDaoService.getInstance();
                profile = profileDaoService.updateProfile(profile, connection);
            }

            if (user != null && profile != null) {
                connection.commit();

                student.setUser(user);
                student.setProfile(profile);;
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
        return null;
    }

    @Override
    public List<Student> all() throws DaoException {
        return null;
    }
}
