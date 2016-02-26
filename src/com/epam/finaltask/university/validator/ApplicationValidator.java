package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Application;

import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class ApplicationValidator {
    private static final long MIN_ID = 1;

    public static boolean validateApplication(Application application) {
        if (!validateId(application.getFacultyId())) {
            return false;
        }
        if (!validateId(application.getProfileId())) {
            return false;
        }
        if (!validateDate(application.getDate())) {
            return false;
        }

        return true;
    }

    public static boolean validateId(long id) {
        return id >= MIN_ID;
    }

    public static boolean validateDate(Calendar date) {
        return date.getTimeInMillis() <= System.currentTimeMillis();
    }
}
