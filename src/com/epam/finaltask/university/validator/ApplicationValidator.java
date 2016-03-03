package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Application;

import java.util.Calendar;


/**
 * Application validator.
 */
public class ApplicationValidator {
    private static final long MIN_ID = 1;

    /**
     * Validate application boolean.
     *
     * @param application the application
     * @return true if valid, else false
     */
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

    /**
     * Validate id.
     *
     * @param id the id
     * @return true if valid, else false
     */
    public static boolean validateId(long id) {
        return id >= MIN_ID;
    }

    /**
     * Validate date.
     *
     * @param date the date
     * @return true if valid, else false
     */
    public static boolean validateDate(Calendar date) {
        return date.getTimeInMillis() <= System.currentTimeMillis();
    }
}
