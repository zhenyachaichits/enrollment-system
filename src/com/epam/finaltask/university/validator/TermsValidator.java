package com.epam.finaltask.university.validator;

import com.epam.finaltask.university.bean.Terms;

import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 02.03.2016.
 */
public class TermsValidator {

    public static boolean validateTerms(Terms terms) {
        if (terms.getStartDate().getTimeInMillis() >= terms.getEndDate().getTimeInMillis()) {
            return false;
        }
        return true;
    }
}
