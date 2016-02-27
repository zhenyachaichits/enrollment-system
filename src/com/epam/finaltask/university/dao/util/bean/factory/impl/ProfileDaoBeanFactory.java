package com.epam.finaltask.university.dao.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.util.DateTypeConverter;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class ProfileDaoBeanFactory implements DaoBeanFactory<Profile> {

    private ProfileDaoBeanFactory() { }

    public static class ProfileDaoConstructorHolder {
        public static final ProfileDaoBeanFactory INSTANCE = new ProfileDaoBeanFactory();
    }

    public static ProfileDaoBeanFactory getInstance() {
        return ProfileDaoConstructorHolder.INSTANCE;
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
    private static final String FORM_KEY = "free_form";
    private static final String MEDAL_TYPE_KEY = "medal";
    private static final String PRIVILEGES_KEY = "privilegies";
    private static final String APPLIED_KEY = "applied";
    private static final String FACULTY_ID_KEY = "faculty_faculty_id";
    private static final String USER_ID_KEY = "user_user_id";

    @Override
    public Profile construct(ResultSet resultSet) throws SQLException {
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
        profile.setFreeForm(resultSet.getBoolean(FORM_KEY));
        profile.setApplied(resultSet.getBoolean(APPLIED_KEY));
        String privileges = resultSet.getString(PRIVILEGES_KEY);
        if (!resultSet.wasNull()) {
            profile.setPrivileges(privileges);
        }
        profile.setFacultyId(resultSet.getLong(FACULTY_ID_KEY));
        profile.setUserId(resultSet.getLong(USER_ID_KEY));

        return profile;
    }
}
