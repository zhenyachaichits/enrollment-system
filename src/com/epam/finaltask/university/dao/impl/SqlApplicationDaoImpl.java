package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ApplicationDao;
import com.epam.finaltask.university.dao.common.ApplicationCommon;
import com.epam.finaltask.university.dao.common.ProfileCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.constructor.DaoConstructor;
import com.epam.finaltask.university.dao.util.constructor.impl.ApplicationDaoConstructor;

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

    private static final String ID_KEY = "faculty_id";
    private static final String NAME_KEY = "name";
    private static final String FREE_QUOTA_KEY = "free_quota";

    private static final String FIND_ALL_APPLICATIONS_QUERY = "SELECT * FROM application WHERE status = 'ACTIVE'";
    private static final String FIND_BY_PROFILE_ID_QUERY = "SELECT * FROM application WHERE " +
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
                DaoConstructor<Application> constructor = ApplicationDaoConstructor.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Application update(Application entity) throws DaoException {
        return null;
    }

    @Override
    public Application delete(Long domain) throws DaoException {
        return null;
    }

    @Override
    public List<Application> all() throws DaoException {
        return null;
    }

}
