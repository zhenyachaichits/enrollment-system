package com.epam.finaltask.university.dao.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.dao.util.DateTypeConverter;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


/**
 * Application dao bean factory.
 */
public class ApplicationDaoBeanFactory implements DaoBeanFactory<Application> {

    private ApplicationDaoBeanFactory() {
    }

    public static class ApplicationDaoConstructorHolder {
        public static final ApplicationDaoBeanFactory INSTANCE = new ApplicationDaoBeanFactory();
    }

    public static ApplicationDaoBeanFactory getInstance() {
        return ApplicationDaoConstructorHolder.INSTANCE;
    }

    private static final String ID_KEY = "application_id";
    private static final String COMPETITION_KEY = "out_of_competition";
    private static final String DATE_KEY = "date";
    private static final String CONFIRMED_KEY = "confirmed";
    private static final String PROFILE_ID_KEY = "profile_profile_id";
    private static final String FACULTY_ID_KEY = "profile_faculty_faculty_id";

    /**
     * Constructs Application bean from ResultSet
     *
     * @param resultSet the result set
     * @return created application
     * @throws SQLException
     */
    @Override
    public Application construct(ResultSet resultSet) throws SQLException {
        Application application = new Application();

        long applicationId = resultSet.getLong(ID_KEY);
        boolean isOutCompetition = resultSet.getBoolean(COMPETITION_KEY);
        Calendar date = DateTypeConverter.convertToCalendar(resultSet.getDate(DATE_KEY));
        boolean isConfirmed = resultSet.getBoolean(CONFIRMED_KEY);
        long profileId = resultSet.getLong(PROFILE_ID_KEY);
        long facultyId = resultSet.getLong(FACULTY_ID_KEY);

        application.setId(applicationId);
        application.setOutOfCompetition(isOutCompetition);
        application.setDate(date);
        application.setConfirmed(isConfirmed);
        application.setProfileId(profileId);
        application.setFacultyId(facultyId);

        return application;
    }
}
