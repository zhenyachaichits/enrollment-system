package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zheny Chaichits on 01.03.2016.
 */
public class FacultyCommandBeanFactory implements CommandBeanFactory<Faculty> {

    private FacultyCommandBeanFactory() {
    }

    public static class FacultyBeanFactoryHolder {
        public static final FacultyCommandBeanFactory INSTANCE = new FacultyCommandBeanFactory();
    }

    public static FacultyCommandBeanFactory getInstance() {
        return FacultyBeanFactoryHolder.INSTANCE;
    }

    @Override
    public Faculty constructBean(HttpServletRequest request) throws CommandBeanFactoryException {
        try {
            String name = request.getParameter(RequestParameterName.FACULTY_NAME);
            String freeQuotaStr = request.getParameter(RequestParameterName.FREE_QUOTA);
            String paidQuotaStr = request.getParameter(RequestParameterName.PAID_QUOTA);
            String termsIdStr = request.getParameter(RequestParameterName.TERMS_ID);
            String[] subjects = request.getParameterValues(RequestParameterName.SUBJECTS);

            int freeQuota = Integer.parseInt(freeQuotaStr);
            int paidQuota = Integer.parseInt(paidQuotaStr);
            long termsId = Long.parseLong(termsIdStr);

            Set<Long> subjectsId = new HashSet<>(subjects.length);
            for (String subjectIdStr : subjects) {
                subjectsId.add(Long.parseLong(subjectIdStr));
            }

            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setFreeQuota(freeQuota);
            faculty.setPaidQuota(paidQuota);
            faculty.setTermsId(termsId);
            faculty.setSubjects(subjectsId);

            return faculty;
        } catch (NumberFormatException e) {
            throw new CommandBeanFactoryException("Couldn't build faculty bean", e);
        }
    }
}
