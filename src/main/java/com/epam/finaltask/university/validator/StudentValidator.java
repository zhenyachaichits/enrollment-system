package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.User;


/**
 * Student validator.
 */
public class StudentValidator {

    /**
     * Validate student.
     *
     * @param student the student
     * @return true if valid, else false
     */
    public static boolean validateStudent(Student student) {
        User user = student.getUser();
        Profile profile = student.getProfile();

        boolean isUserValid = UserValidator.validateUser(user);
        boolean isProfileValid = ProfileValidator.validateProfile(profile);

        return isUserValid && isProfileValid;
    }

    /**
     * Validate student for update.
     *
     * @param student the student
     * @return true if valid, else false
     */
    public static boolean validateStudentForUpdate(Student student) {
        User user = student.getUser();
        Profile profile = student.getProfile();

        if (!ProfileValidator.validateProfile(profile)) {
            return false;
        }
        if (!UserValidator.validateEmail(user.getEmail())) {
            return false;
        }
        if (!"".equals(user.getPassword()) && !UserValidator.validatePassword(user.getPassword())) {
            return false;
        }

        return true;
    }
}
