package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Terms;

import java.util.Calendar;

/**
 * Terms validator.
 */
public class TermsValidator {

    /**
     * Validate terms.
     *
     * @param terms the terms
     * @return true if valid, else false
     */
    public static boolean validateTerms(Terms terms) {
        if (terms.getStartDate().getTimeInMillis() >= terms.getEndDate().getTimeInMillis()) {
            return false;
        }
        return true;
    }
}
