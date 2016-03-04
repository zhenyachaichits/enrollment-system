package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.dao.util.DateTypeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Application dao common methods which can be used for transactions ect..
 */
public class ApplicationCommon {

    private ApplicationCommon() {
    }

    public static class ApplicationCommonHolder {

        public static final ApplicationCommon INSTANCE = new ApplicationCommon();
    }

    public static ApplicationCommon getInstance() {
        return ApplicationCommonHolder.INSTANCE;
    }

    private static final String ADD_APPLICATION_QUERY = "INSERT INTO application (out_of_competition, date, " +
            "profile_profile_id, profile_faculty_faculty_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE_APPLICATION_QUERY = "UPDATE application SET status = 'DELETED' WHERE " +
            "profile_profile_id = ?";


    /**
     * Create application.
     *
     * @param application the application
     * @param connection  the connection
     * @return the application
     * @throws SQLException the sql exception
     */
    public Application createApplication(Application application, Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(ADD_APPLICATION_QUERY);
        ) {

            statement.setBoolean(1, application.isOutOfCompetition());
            statement.setDate(2, DateTypeConverter.convertToSqlDate(application.getDate()));
            statement.setLong(3, application.getProfileId());
            statement.setLong(4, application.getFacultyId());

            int result = statement.executeUpdate();

            if (result != 0) {
                return application;
            } else {
                return null;
            }
        }
    }

    /**
     * Delete application.
     *
     * @param profileId  the profile id
     * @param connection the connection
     * @return true if application was deleted, false if not
     * @throws SQLException the sql exception
     */
    public boolean deleteApplication(Long profileId, Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE_APPLICATION_QUERY);
        ) {
            statement.setLong(1, profileId);

            int result = statement.executeUpdate();

            return result != 0;
        }
    }
}
