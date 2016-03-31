package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.User;


/**
 * User validator.
 */
public class UserValidator {

    private static final int MIN_PASSWORD_LENGTH = 5;

    private static final String EMAIL_PATTERN = "[a-zA-Z0-9_\\.-]+@[a-zA-Z0-9_-]+(\\..*)?";

    /**
     * Validate user.
     *
     * @param user the user
     * @return true if valid, else false
     */
    public static boolean validateUser(User user) {
        if (!validateEmail(user.getEmail())) {
            return false;
        }
        if (!validatePassword(user.getPassword())) {
            return false;
        }

        return true;
    }

    /**
     * Validate user for update.
     *
     * @param user the user
     * @return true if valid, else false
     */
    public static boolean validateUserForUpdate(User user) {
        if (!validateEmail(user.getEmail())) {
            return false;
        }

        if (!"".equals(user.getPassword()) && !validatePassword(user.getPassword())) {
            return false;
        }

        return true;
    }

    /**
     * Validate email.
     *
     * @param email the email
     * @return true if valid, else false
     */
    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    /**
     * Validate password.
     *
     * @param password the password
     * @return true if valid, else false
     */
    public static boolean validatePassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

}

