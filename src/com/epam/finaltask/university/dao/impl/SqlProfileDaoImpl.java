package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.common.ProfileCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.constructor.DaoConstructor;
import com.epam.finaltask.university.dao.util.constructor.impl.ProfileDaoConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class SqlProfileDaoImpl implements ProfileDao {

    private final ConnectionPool connectionPool;

    private SqlProfileDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class ProfileDaoHolder {
        public static final SqlProfileDaoImpl INSTANCE = new SqlProfileDaoImpl();
    }

    public static SqlProfileDaoImpl getInstance() {
        return ProfileDaoHolder.INSTANCE;
    }

    private static final String FIND_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile WHERE passport_id = ? " +
            "AND status = 'ACTIVE' AND applied = FALSE";
    private static final String FIND_PROFILE_BY_LAST_NAME_QUERY = "SELECT * FROM profile WHERE last_name = ? " +
            "AND status = 'ACTIVE' AND applied = FALSE";
    private static final String FIND_APPLIED_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile WHERE passport_id = ? " +
            "AND status = 'ACTIVE' AND applied = TRUE";
    private static final String FIND_APPLIED_PROFILE_BY_LAST_NAME_QUERY = "SELECT * FROM profile WHERE last_name = ? " +
            "AND status = 'ACTIVE' AND applied = TRUE";
    private static final String FIND_PROFILE_BY_ID_QUERY = "SELECT * FROM profile WHERE profile_id = ? " +
            "AND status = 'ACTIVE'";
    private static final String GET_ALL_PROFILES_QUERY = "SELECT * FROM profile WHERE status = 'ACTIVE' " +
            "AND applied = FALSE";
    private static final String CHECK_UPDATE_AVAILABILITY = "SELECT profile_id FROM profile " +
            "WHERE user_user_id <> ? AND passport_id = ? AND status = 'ACTIVE'";

    @Override
    public Profile add(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            ProfileCommon service = ProfileCommon.getInstance();
            profile = service.createProfile(profile, connection);

            return profile;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile find(String passportId) throws DaoException {
        return findByPassportId(passportId, false);
    }

    @Override
    public List<Profile> findProfileByLastName(String lastName) throws DaoException {
        return  findByLastName(lastName, false);
    }

    @Override
    public Profile findById(long profileId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_PROFILE_BY_ID_QUERY);
        ) {
            statement.setLong(1, profileId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DaoConstructor<Profile> constructor = ProfileDaoConstructor.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile update(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            ProfileCommon profileCommon = ProfileCommon.getInstance();
            profile = profileCommon.updateProfile(profile, connection);

            return profile;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile delete(String domain) throws DaoException {
        return null;
    }

    @Override
    public List<Profile> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Profile> profiles = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(GET_ALL_PROFILES_QUERY);
            while (resultSet.next()) {
                DaoConstructor<Profile> constructor = ProfileDaoConstructor.getInstance();

                profiles.add(constructor.construct(resultSet));
            }

            return profiles;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public boolean checkUpdateAvailability(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY);
        ) {
            statement.setLong(1, profile.getUserId());
            statement.setString(2, profile.getPassportId());

            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile findApplied(String passportId) throws DaoException {
        return findByPassportId(passportId, true);
    }

    @Override
    public List<Profile> findAppliedByLastName(String lastName) throws DaoException {
        return findByLastName(lastName, true);
    }

    private Profile findByPassportId(String passportId, boolean isApplied) throws DaoException {
        String query;
        if (isApplied) {
            query = FIND_APPLIED_PROFILE_BY_PASSPORT_ID_QUERY;
        } else {
            query = FIND_PROFILE_BY_PASSPORT_ID_QUERY;
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, passportId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DaoConstructor<Profile> constructor = ProfileDaoConstructor.getInstance();
                ;

                return constructor.construct(resultSet);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    private List<Profile> findByLastName(String lastName, boolean isApplied) throws DaoException {
        String query;
        if (isApplied) {
            query = FIND_APPLIED_PROFILE_BY_LAST_NAME_QUERY;
        } else {
            query = FIND_PROFILE_BY_LAST_NAME_QUERY;
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            List<Profile> profiles = new ArrayList<>();
            statement.setString(1, lastName);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DaoConstructor<Profile> constructor = ProfileDaoConstructor.getInstance();

                profiles.add(constructor.construct(resultSet));
            }

            return profiles;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}
