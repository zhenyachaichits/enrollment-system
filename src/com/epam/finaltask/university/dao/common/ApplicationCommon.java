package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.dao.util.DateTypeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 25.02.2016.
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
}
