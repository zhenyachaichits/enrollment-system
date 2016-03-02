package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ApplicationDao;
import com.epam.finaltask.university.dao.common.ApplicationCommon;
import com.epam.finaltask.university.dao.common.ProfileCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.ApplicationDaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.ProfileDaoBeanFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class SqlApplicationDaoImpl implements ApplicationDao {

    private final ConnectionPool connectionPool;

    private SqlApplicationDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class ApplicationDaoHolder {
        public static final SqlApplicationDaoImpl INSTANCE = new SqlApplicationDaoImpl();
    }

    public static SqlApplicationDaoImpl getInstance() {
        return ApplicationDaoHolder.INSTANCE;
    }

    private static final int ZERO = 0;
    private static final String COUNT_KEY = "count";
    private static final String POINT_KEY = "point";

    private static final String FIND_ALL_APPLICATIONS_QUERY = "SELECT * FROM application WHERE status = 'ACTIVE'";
    private static final String FIND_BY_PROFILE_ID_QUERY = "SELECT * FROM application WHERE " +
            "profile_profile_id = ? AND status = 'ACTIVE'";
    private static final String FIND_OUT_COMPETITION_NUMBER_QUERY = "SELECT COUNT(*) AS count FROM application " +
            "INNER JOIN profile ON profile.profile_id = application.profile_profile_id WHERE " +
            "application.out_of_competition = TRUE AND profile.faculty_faculty_id = ? AND " +
            "profile.free_form = ? AND profile.status = 'ACTIVE' AND application.status = 'ACTIVE';";
    private static final String FIND_MIN_POINTS_QUERY = "SELECT MIN(profile.points) AS point FROM application " +
            "INNER JOIN profile ON profile.profile_id = application.profile_profile_id " +
            "WHERE application.profile_faculty_faculty_id = ? AND application.out_of_competition = ? " +
            "AND profile.free_form = ? AND application.status = 'ACTIVE' " +
            "AND profile.status = 'ACTIVE' ORDER BY points DESC LIMIT ?";
    private static final String CONFIRM_APPLICATION_QUERY = "UPDATE application SET confirmed = TRUE WHERE " +
            "profile_profile_id = ? AND status = 'ACTIVE'";

    @Override
    public Application add(Application application) throws DaoException {

        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            ApplicationCommon applicationCommon = ApplicationCommon.getInstance();
            ProfileCommon profileCommon = ProfileCommon.getInstance();

            Profile profile = new Profile();
            profile.setApplied(true);
            profile.setId(application.getProfileId());

            application = applicationCommon.createApplication(application, connection);

            if (application != null && profileCommon.updateProfileApplicationStatus(profile, connection)) {
                connection.commit();
                return application;
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
    public Application find(Long profileId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_PROFILE_ID_QUERY);
        ) {
            statement.setLong(1, profileId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DaoBeanFactory<Application> constructor = ApplicationDaoBeanFactory.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Application update(Application entity) {
        throw new UnsupportedOperationException("Application can't be updated");
    }

    @Override
    public Application delete(Long profileId) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            ApplicationCommon applicationCommon = ApplicationCommon.getInstance();
            ProfileCommon profileCommon = ProfileCommon.getInstance();

            Application application = new Application();
            application.setProfileId(profileId);
            Profile profile = new Profile();
            profile.setApplied(false);
            profile.setId(profileId);

            boolean isApplicationDeleted = applicationCommon.deleteApplication(profileId, connection);
            boolean isProfileUpdated = profileCommon.updateProfileApplicationStatus(profile, connection);

            if (isApplicationDeleted && isProfileUpdated) {
                connection.commit();
                return application;
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
    public List<Application> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Application> applications = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_APPLICATIONS_QUERY);
            while (resultSet.next()) {
                DaoBeanFactory<Application> constructor = ApplicationDaoBeanFactory.getInstance();

                applications.add(constructor.construct(resultSet));
            }

            return applications;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public int findOutCompetitionNumber(long facultyId, boolean isFreeForm) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_OUT_COMPETITION_NUMBER_QUERY);
        ) {
            statement.setLong(1, facultyId);
            statement.setBoolean(2, isFreeForm);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? resultSet.getInt(COUNT_KEY) : ZERO;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public int findMinPoints(long facultyId, int quota, boolean isOutOfCompetition, boolean isFreeForm)
            throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_MIN_POINTS_QUERY);
        ) {
            statement.setLong(1, facultyId);
            statement.setBoolean(2, isOutOfCompetition);
            statement.setBoolean(3, isFreeForm);
            statement.setInt(4, quota);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int points = resultSet.getInt(POINT_KEY);

            return resultSet.wasNull() ? ZERO : points;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public boolean confirmApplication(long profileId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CONFIRM_APPLICATION_QUERY);
        ) {
            statement.setLong(1, profileId);;

            int result = statement.executeUpdate();

            return result != 0;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

}
