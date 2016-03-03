package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Faculty;

import java.util.Set;


/**
 * Faculty validator.
 */
public class FacultyValidator {

    private static final int MIN_NUMBER = 1;
    private static final int SUBJECTS_NUMBER = 3;

    private static final String NAME_PATTERN = ".{2,}";

    /**
     * Validate faculty.
     *
     * @param faculty the faculty
     * @return true if valid, else false
     */
    public static boolean validateFaculty(Faculty faculty) {
        if (!validateName(faculty.getName())) {
            return false;
        }
        if (!validateNumber(faculty.getFreeQuota())) {
            return false;
        }
        if (!validateNumber(faculty.getPaidQuota())) {
            return false;
        }
        if (!validateNumber(faculty.getTermsId())) {
            return false;
        }
        if (faculty.getSubjects() != null && !validateSubjects(faculty.getSubjects())) {
            return false;
        }

        return true;
    }


    /**
     * Validate name.
     *
     * @param name the name
     * @return true if valid, else false
     */
    public static boolean validateName(String name) {
        return name.matches(NAME_PATTERN);
    }

    /**
     * Validate number.
     *
     * @param number the number
     * @return true if valid, else false
     */
    public static boolean validateNumber(long number) {
        return number >= MIN_NUMBER;
    }

    /**
     * Validate subjects.
     *
     * @param subjects the subjects
     * @return true if valid, else false
     */
    public static boolean validateSubjects(Set<Long> subjects) {
        return subjects.size() == SUBJECTS_NUMBER;
    }

}
