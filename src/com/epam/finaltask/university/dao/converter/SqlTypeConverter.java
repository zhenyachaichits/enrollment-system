package com.epam.finaltask.university.dao.converter;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class SqlTypeConverter {
    //todo: ask about it
    public static java.sql.Date convertToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date convertToUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }
}
