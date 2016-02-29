package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.User;

/**
 * Created by Zheny Chaichits on 10.02.2016.
 */
public class UserValidator {

    private static final int MIN_ID = 1;
    private static final int MIN_PASSWORD_LENGTH = 5;

    private static final String EMAIL_PATTERN = "[a-zA-Z0-9_\\.]+@[a-zA-Z]+(\\..*)?";

    public static boolean validateUser(User user) {
        if (!validateEmail(user.getEmail())) {
            return false;
        }
        if (!validatePassword(user.getPassword())) {
            return false;
        }

        return true;
    }

    public static boolean validateUserForUpdate(User user) {
        if (!validateEmail(user.getEmail())) {
            return false;
        }

        if (!"".equals(user.getPassword()) && !validatePassword(user.getPassword())) {
            return false;
        }

        return true;
    }

    public static boolean validateEmail(String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean validatePassword(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

}

