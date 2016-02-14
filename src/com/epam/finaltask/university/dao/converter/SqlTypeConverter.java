package com.epam.finaltask.university.dao.converter;

import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class SqlTypeConverter {

    public static java.sql.Date convertToSqlDate(Calendar calendar) {
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    public static Calendar convertToUtilDate(java.sql.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
