package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Profile;

import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class ProfileValidator {

    private static final int MIN_POINTS = 0;

    private static final String NAME_PATTERN = ".{2,}";
    private static final String PASSPORT_ID_PATTERN = "[a-zA-Zа-яА-Я]{2}\\d{7}";
    private static final String PHONE_PATTERN = "\\+\\d{3}\\s?\\(?\\d{2}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}";

    public static boolean validateProfile(Profile profile) {
        if (!validatePassportId(profile.getPassportId())) {
            return false;
        }
        if (!validateText(profile.getFirstName())) {
            return false;
        }
        if (!validateText(profile.getMiddleName())) {
            return false;
        }
        if (!validateText(profile.getLastName())) {
            return false;
        }
        if (!validateBirthDate(profile.getBirthDate())) {
            return false;
        }
        if (!validatePhone(profile.getPhone())) {
            return false;
        }
        if (!validateText(profile.getAddress())) {
            return false;
        }
        if (!validatePoints(profile.getPoints())) {
            return false;
        }
        return true;
    }

    public static boolean validateText(String text) {
        return  text.matches(NAME_PATTERN);
    }

    public static boolean validatePassportId(String passportId) {
        return passportId.matches(PASSPORT_ID_PATTERN);
    }

    public static boolean validatePhone(String phone) {
        return phone.matches(PHONE_PATTERN);
    }

    public static boolean validatePoints(int points) {
        return points > MIN_POINTS;
    }

    public static boolean validateBirthDate(Calendar birthDate) {
        return birthDate.getTimeInMillis() < System.currentTimeMillis();
    }

}
