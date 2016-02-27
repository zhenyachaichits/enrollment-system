package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.util.DateTypeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class ProfileCommon {

    private ProfileCommon() {
    }

    public static class ProfileCommonHolder {
        public static final ProfileCommon INSTANCE = new ProfileCommon();
    }

    public static ProfileCommon getInstance() {
        return ProfileCommonHolder.INSTANCE;
    }

    private static final int MIN_PARAMETER_INDEX = 1;

    private static final String ADD_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, birth_date," +
            "phone, address, points, medal, free_form, faculty_faculty_id, user_user_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_PRIVILEGED_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, " +
            "birth_date, phone, address, points, medal, free_form, privilegies, faculty_faculty_id, user_user_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PROFILE_QUERY = "UPDATE profile SET passport_id = ?, first_name = ?, " +
            "middle_name = ?, last_name = ?, birth_date = ?, phone = ?, address = ?, points = ?, " +
            "medal = ?, free_form = ?, faculty_faculty_id = ? WHERE user_user_id = ?";
    private static final String UPDATE_PRIVILEGED_PROFILE_QUERY = "UPDATE profile SET passport_id = ?, first_name = ?, " +
            "middle_name = ?, last_name = ?, birth_date = ?, phone = ?, address = ?, points = ?, " +
            "medal = ?, free_form = ?, privilegies = ?, faculty_faculty_id = ? WHERE user_user_id = ?";
    private static final String UPDATE_APPLICATION_STATUS_QUERY = "UPDATE profile SET applied = ? WHERE profile_id = ?";
    private static final String DELETE_PROFILE_QUERY = "UPDATE profile SET status = 'DELETED' " +
            "WHERE user_user_id = ?";

    public Profile createProfile(Profile profile, Connection connection) throws SQLException {
        String query;

        if (profile.getPrivileges() == null) {
            query = ADD_PROFILE_QUERY;
        } else {
            query = ADD_PRIVILEGED_PROFILE_QUERY;
        }
        try (
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            fillInStatement(statement, profile);
            int result = statement.executeUpdate();
            ;

            if (result != 0) {
                return profile;
            } else {
                return null;
            }
        }
    }

    public Profile updateProfile(Profile profile, Connection connection) throws SQLException {
        String query;

        if (profile.getPrivileges() == null) {
            query = UPDATE_PROFILE_QUERY;
        } else {
            query = UPDATE_PRIVILEGED_PROFILE_QUERY;
        }
        try (
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            fillInStatement(statement, profile);
            int result = statement.executeUpdate();

            if (result != 0) {
                return profile;
            } else {
                return null;
            }
        }
    }

    public boolean deleteProfile(Long userId, Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE_PROFILE_QUERY);
        ) {
            statement.setLong(1, userId);
            int result = statement.executeUpdate();

            return result != 0;
        }
    }

    public boolean updateProfileApplicationStatus(Profile profile, Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE_APPLICATION_STATUS_QUERY);
        ) {
            statement.setBoolean(1, profile.isApplied());
            statement.setLong(2, profile.getId());

            int result = statement.executeUpdate();

            return result != 0;
        }
    }

    private void fillInStatement(PreparedStatement statement, Profile profile) throws SQLException {
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
        statement.setBoolean(i++, profile.isFreeForm());
        if (profile.getPrivileges() != null) {
            statement.setString(i++, profile.getPrivileges());
        }
        statement.setLong(i++, profile.getFacultyId());
        statement.setLong(i, profile.getUserId());
    }

}
