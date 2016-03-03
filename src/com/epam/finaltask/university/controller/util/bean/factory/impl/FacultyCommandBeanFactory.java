package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;


/**
 * Faculty command bean factory.
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

    /**
     * Creates new faculty bean from request parameters
     * @param request
     * @return new faculty bean reference
     * @throws CommandBeanFactoryException
     */
    @Override
    public Faculty constructBean(HttpServletRequest request) throws CommandBeanFactoryException {
        try {
            Faculty faculty = new Faculty();

            String name = request.getParameter(RequestParameterName.FACULTY_NAME);
            String freeQuotaStr = request.getParameter(RequestParameterName.FREE_QUOTA);
            String paidQuotaStr = request.getParameter(RequestParameterName.PAID_QUOTA);
            String termsIdStr = request.getParameter(RequestParameterName.TERMS_ID);
            String[] subjects = request.getParameterValues(RequestParameterName.SUBJECTS);

            int freeQuota = Integer.parseInt(freeQuotaStr);
            int paidQuota = Integer.parseInt(paidQuotaStr);
            long termsId = Long.parseLong(termsIdStr);

            if (subjects != null) {
                Set<Long> subjectsId = new HashSet<>(subjects.length);
                for (String subjectIdStr : subjects) {
                    subjectsId.add(Long.parseLong(subjectIdStr));
                }

                faculty.setSubjects(subjectsId);
            }

            faculty.setName(name);
            faculty.setFreeQuota(freeQuota);
            faculty.setPaidQuota(paidQuota);
            faculty.setTermsId(termsId);

            return faculty;
        } catch (NumberFormatException e) {
            throw new CommandBeanFactoryException("Couldn't build faculty bean", e);
        }
    }
}
