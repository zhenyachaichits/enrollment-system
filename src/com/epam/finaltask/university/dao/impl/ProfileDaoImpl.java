package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.common.ProfileDaoService;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.util.DateTypeConverter;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class ProfileDaoImpl implements ProfileDao {

    private final ConnectionPool connectionPool;

    private ProfileDaoImpl() {
            connectionPool = ConnectionPool.getInstance();
    }

    public static class ProfileDaoHolder {
        public static final ProfileDaoImpl INSTANCE = new ProfileDaoImpl();
    }

    public static ProfileDaoImpl getInstance() {
        return ProfileDaoHolder.INSTANCE;
    }

    private static final String FIND_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile WHERE passport_id = ?";

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
        return null;
    }
}
