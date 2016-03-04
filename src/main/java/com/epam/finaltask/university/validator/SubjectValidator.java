package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Subject;


/**
 * Subject validator.
 */
public class SubjectValidator {

    private static final int MIN_POINTS_FLOOR = 1;
    private static final int MIN_POINTS_CEIL = 50;

    private static final String NAME_PATTERN = ".{2,}";

    /**
     * Validate subject.
     *
     * @param subject the subject
     * @return true if valid, else false
     */
    public static boolean validateSubject(Subject subject) {
        if (!validateName(subject.getName())) {
            return false;
        }
        if (!validatePoints(subject.getMinPoint())) {
            return false;
        }
        return true;
    }

    /**
     * Validate name.
     *
     * @param text the text
     * @return true if valid, else false
     */
    public static boolean validateName(String text) {
        return text.matches(NAME_PATTERN);
    }

    /**
     * Validate points.
     *
     * @param points the points
     * @return true if valid, else false
     */
    public static boolean validatePoints(int points) {
        return points >= MIN_POINTS_FLOOR && points <= MIN_POINTS_CEIL;
    }

}
