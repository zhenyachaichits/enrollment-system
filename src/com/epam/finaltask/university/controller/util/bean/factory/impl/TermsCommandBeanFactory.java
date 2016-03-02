package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 02.03.2016.
 */
public class TermsCommandBeanFactory implements CommandBeanFactory<Terms> {

    private TermsCommandBeanFactory() {
    }

    public static class TermsBeanFactoryHolder {
        public static final TermsCommandBeanFactory INSTANCE = new TermsCommandBeanFactory();
    }

    public static TermsCommandBeanFactory getInstance() {
        return TermsBeanFactoryHolder.INSTANCE;
    }

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public Terms constructBean(HttpServletRequest request) throws CommandBeanFactoryException {
        try {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

            String startStr = request.getParameter(RequestParameterName.START_DATE);
            String endStr = request.getParameter(RequestParameterName.END_DATE);

            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            startDate.setTime(formatter.parse(startStr));
            endDate.setTime(formatter.parse(endStr));

            Terms terms = new Terms();
            terms.setStartDate(startDate);
            terms.setEndDate(endDate);

            return terms;
        } catch (ParseException e) {
            throw new CommandBeanFactoryException("Couldn't build terms bean", e);
        }
    }
}
