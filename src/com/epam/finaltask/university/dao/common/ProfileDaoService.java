package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.util.DateTypeConverter;

import java.sql.*;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class ProfileDaoService {

    private ProfileDaoService() { }

    public static class ProfileDaoServiceHolder {
        public static final ProfileDaoService INSTANCE = new ProfileDaoService();
    }

    public static ProfileDaoService getInstance() {
        return ProfileDaoServiceHolder.INSTANCE;
    }

    private static final String PROFILE_ID_KEY = "profile_id";
    private static final String PASSPORT_ID_KEY = "passport_id";
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String MIDDLE_NAME_KEY = "middle_name";
    private static final String LAST_NAME_KEY = "last_name";
    private static final String BIRTH_DATE_KEY = "birth_date";
    private static final String PHONE_KEY = "phone";
    private static final String ADDRESS_KEY = "address";
    private static final String POINTS_KEY = "points";
    private static final String MEDAL_TYPE_KEY = "medal";
    private static final String PRIVILEGES_KEY = "privilegies";
    private static final String FACULTY_ID_KEY = "faculty_faculty_id";
    private static final String USER_ID_KEY = "user_user_id";

    private static final int MIN_PARAMETER_INDEX = 1;

    private static final String ADD_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, birth_date," +
            "phone, address, points, medal, faculty_faculty_id, user_user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_PRIVILEGED_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, " +
            "birth_date, phone, address, points, medal, privilegies, faculty_faculty_id, user_user_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public Profile createProfile(Profile profile, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        try {
            String query;

            if (profile.getPrivileges() == null) {
                query = ADD_PROFILE_QUERY;
            } else {
                query = ADD_PRIVILEGED_PROFILE_QUERY;
            }

            statement = connection.prepareStatement(query);

            int i = MIN_PARAMETER_INDEX;
            statement.setString(i++, profile.getPassportId());
            statement.setString(i++, profile.getFirstName());
            statement.setString(i++, profile.getMiddleName());
            statement.setString(i++, profile.getLastName());
            statement.setDate(i++, DateTypeConverter.convertToSqlDate(profile.getBirthDate()));
            statement.setString(i++, profile.getPhone());
            statement.setString(i++, profile.getAddress());
            statement.setInt(i++, profile.getPoints());
            statement.setString(i++, profile.getMedalType().toString());
            if (profile.getPrivileges() != null) {
                statement.setString(i++, profile.getPrivileges());
            }
            statement.setLong(i++, profile.getFacultyId());
            statement.setLong(i, profile.getUserId());

            int result = statement.executeUpdate();

            if (result != 0) {
                return profile;
            } else {
                return null;
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public Profile compileProfile(ResultSet resultSet) throws SQLException {
        Profile profile = new Profile();

        profile.setId(resultSet.getLong(PROFILE_ID_KEY));
        profile.setPassportId(resultSet.getString(PASSPORT_ID_KEY));
        profile.setFirstName(resultSet.getString(FIRST_NAME_KEY));
        profile.setMiddleName(resultSet.getString(MIDDLE_NAME_KEY));
        profile.setLastName(resultSet.getString(LAST_NAME_KEY));
        profile.setBirthDate(DateTypeConverter.convertToCalendar(resultSet.getDate(BIRTH_DATE_KEY)));
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
    }

}
