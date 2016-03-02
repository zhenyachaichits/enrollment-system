package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Faculty;

import java.util.Set;

/**
 * Created by Zheny Chaichits on 01.03.2016.
 */
public class FacultyValidator {

    private static final int MIN_NUMBER = 1;
    private static final int SUBJECTS_NUMBER = 3;

    private static final String NAME_PATTERN = ".{2,}";

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


    public static boolean validateName(String name) {
        return name.matches(NAME_PATTERN);
    }

    public static boolean validateNumber(long number) {
        return number >= MIN_NUMBER;
    }

    public static boolean validateSubjects(Set<Long> subjects) {
        return subjects.size() == SUBJECTS_NUMBER;
    }

}
