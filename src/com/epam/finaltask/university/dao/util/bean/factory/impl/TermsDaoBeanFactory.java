package com.epam.finaltask.university.dao.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.util.DateTypeConverter;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 28.02.2016.
 */
public class TermsDaoBeanFactory implements DaoBeanFactory<Terms> {

    private TermsDaoBeanFactory() { }

    public static class TermsDaoBeanFactoryHolder {
        public static final TermsDaoBeanFactory INSTANCE = new TermsDaoBeanFactory();
    }

    public static TermsDaoBeanFactory getInstance() {
        return TermsDaoBeanFactoryHolder.INSTANCE;
    }

    private static final String ID_KEY = "terms_id";
    private static final String START_DATE_KEY = "start_date";
    private static final String END_DATE_KEY = "end_date";
    @Override
    public Terms construct(ResultSet resultSet) throws SQLException {
        Terms terms = new Terms();

        long id = resultSet.getLong(ID_KEY);
        Calendar startDate = DateTypeConverter.convertToCalendar(resultSet.getDate(START_DATE_KEY));
        Calendar endDate = DateTypeConverter.convertToCalendar(resultSet.getDate(END_DATE_KEY));

        terms.setId(id);
        terms.setStartDate(startDate);
        terms.setEndDate(endDate);

        return terms;
    }
}
