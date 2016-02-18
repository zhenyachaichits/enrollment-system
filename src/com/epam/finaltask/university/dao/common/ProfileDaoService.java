package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.Profile;
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

}
