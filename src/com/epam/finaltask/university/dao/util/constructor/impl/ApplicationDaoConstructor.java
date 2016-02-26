package com.epam.finaltask.university.dao.util.constructor.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.dao.util.DateTypeConverter;
import com.epam.finaltask.university.dao.util.constructor.DaoConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class ApplicationDaoConstructor implements DaoConstructor<Application> {

    private ApplicationDaoConstructor() { }

    public static class ApplicationDaoConstructorHolder {
        public static final ApplicationDaoConstructor INSTANCE = new ApplicationDaoConstructor();
    }

    public static ApplicationDaoConstructor getInstance() {
        return ApplicationDaoConstructorHolder.INSTANCE;
    }

    private static final String ID_KEY = "application_id";
    private static final String COMPETITION_KEY = "out_of_competition";
    private static final String DATE_KEY = "date";
    private static final String CONFIRMED_KEY = "confirmed";
    private static final String PROFILE_ID_KEY = "profile_profile_id";
    private static final String FACULTY_ID_KEY = "profile_faculty_faculty_id";

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
