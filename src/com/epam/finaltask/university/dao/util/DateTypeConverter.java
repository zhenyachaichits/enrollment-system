package com.epam.finaltask.university.dao.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * Date type converter.
 */
public class DateTypeConverter {

    /**
     * Convert to java.sql.Date to Calendar.
     *
     * @param calendar the calendar
     * @return the java.sql.Date object
     */
    public static Date convertToSqlDate(Calendar calendar) {
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    /**
     * Convert java.sql.Date to Calendar.
     *
     * @param date the date
     * @return the calendar
     */
    public static Calendar convertToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
