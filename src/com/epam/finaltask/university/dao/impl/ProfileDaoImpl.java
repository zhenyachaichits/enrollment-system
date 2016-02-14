package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.converter.SqlTypeConverter;
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

    private static final int MIN_PARAMETER_INDEX = 1;

    private static final String PASSPORT_ID_KEY = "passport_id";
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String MIDDLE_NAME_KEY = "middle_name";
    private static final String LAST_NAME_KEY = "last_name";
    private static final String BIRTH_DATE_KEY = "date_birth";
    private static final String PHONE_KEY = "phone";
    private static final String ADDRESS_KEY = "address";
    private static final String POINTS_KEY = "points";
    private static final String MEDAL_TYPE_KEY = "medal";
    private static final String PRIVILEGES_KEY = "privilegies";
    private static final String FACULTY_ID_KEY = "faculty_faculty_id";
    private static final String USER_ID_KEY = "user_user_id";

    private static final String ADD_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, birth_date," +
            "phone, address, points, medal, faculty_faculty_id, user_user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_PRIVILEGED_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, " +
            "birth_date, phone, address, points, medal, privilegies, faculty_faculty_id, user_user_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile WHERE passport_id = ?";


    @Override
    public Profile add(Profile entity) throws DaoException {
        String query;
        if (entity.getPrivileges() == null) {
            query = ADD_PROFILE_QUERY;
        } else {
            query = ADD_PRIVILEGED_PROFILE_QUERY;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(query);

            int i = MIN_PARAMETER_INDEX;
            statement.setString(i++, entity.getPassportId());
            statement.setString(i++, entity.getFirstName());
            statement.setString(i++, entity.getMiddleName());
            statement.setString(i++, entity.getLastName());
            statement.setDate(i++, SqlTypeConverter.convertToSqlDate(entity.getBirthDate()));
            statement.setString(i++, entity.getPhone());
            statement.setString(i++, entity.getAddress());
            statement.setInt(i++, entity.getPoints());
            statement.setString(i++, entity.getMedalType().toString());
            if (entity.getPrivileges() != null) {
                statement.setString(i++, entity.getPrivileges());
            }
            statement.setLong(i++, entity.getFacultyId());
            statement.setLong(i, entity.getUserId());

            int result = statement.executeUpdate();

            connection.commit();

            if (result == 0) {
                return entity;
            } else {
                return null;
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        } finally {
            if (connection != null && statement != null) {
                connectionPool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public Profile find(String passportId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_PROFILE_BY_PASSPORT_ID_QUERY);
        ) {
            Profile profile = new Profile();
            profile.setPassportId(passportId);

            statement.setString(1, passportId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                profile.setFirstName(resultSet.getString(FIRST_NAME_KEY));
                profile.setMiddleName(resultSet.getString(MIDDLE_NAME_KEY));
                profile.setLastName(resultSet.getString(LAST_NAME_KEY));
                profile.setBirthDate(SqlTypeConverter.convertToUtilDate(resultSet.getDate(BIRTH_DATE_KEY)));
                profile.setPhone(resultSet.getString(PHONE_KEY));
                profile.setAddress(resultSet.getString(ADDRESS_KEY));
                profile.setPoints(resultSet.getInt(POINTS_KEY));
                profile.setMedalType(MedalType.valueOf(resultSet.getString(MEDAL_TYPE_KEY)));
                String privileges = resultSet.getString(PRIVILEGES_KEY);
                if (!resultSet.wasNull()) {
                    profile.setPrivileges(privileges);
                }
                profile.setFacultyId(resultSet.getLong(FACULTY_ID_KEY));
                profile.setUserId(resultSet.getLong(USER_ID_KEY));

                return profile;
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
