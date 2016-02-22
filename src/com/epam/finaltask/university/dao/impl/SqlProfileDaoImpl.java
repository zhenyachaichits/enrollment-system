package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.common.ProfileDaoService;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;

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

    private static final String FIND_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile WHERE passport_id = ?";
    private static final String GET_ALL_PROFILES_QUERY = "SELECT * FROM profile";

    @Override
    public Profile add(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            ProfileDaoService service = ProfileDaoService.getInstance();
            profile = service.createProfile(profile, connection);

            return profile;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile find(String passportId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_PROFILE_BY_PASSPORT_ID_QUERY);
        ) {
            statement.setString(1, passportId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ProfileDaoService service = ProfileDaoService.getInstance();
                return service.compileProfile(resultSet);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile update(Profile entity) throws DaoException {
        return null;
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
                ProfileDaoService service = ProfileDaoService.getInstance();

                profiles.add(service.compileProfile(resultSet));
            }

            return profiles;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}
