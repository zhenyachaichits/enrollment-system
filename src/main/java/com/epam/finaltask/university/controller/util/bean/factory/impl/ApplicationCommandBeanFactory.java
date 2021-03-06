package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;


/**
 * Application command bean factory.
 */
public class ApplicationCommandBeanFactory implements CommandBeanFactory<Application> {

    private ApplicationCommandBeanFactory() {
    }

    public static class ApplicationCompilerHolder {
        public static final ApplicationCommandBeanFactory INSTANCE = new ApplicationCommandBeanFactory();
    }

    public static ApplicationCommandBeanFactory getInstance() {
        return ApplicationCompilerHolder.INSTANCE;
    }

    /**
     * Parameter means that checkbox is checked
     */
    private static final String CHECKED = "on";

    /**
     * Creates new application bean from request parameters
     * @param request
     * @return new application bean reference
     * @throws CommandBeanFactoryException
     */
    @Override
    public Application constructBean(HttpServletRequest request) throws CommandBeanFactoryException {
        try {
            boolean isOutCompetition = CHECKED.equals(request.getParameter(RequestParameterName.OUT_OF_COMPETITION));
            Calendar date = Calendar.getInstance();
            String facultyStr = request.getParameter(RequestParameterName.FACULTY_ID);
            String profileStr = request.getParameter(RequestParameterName.PROFILE_ID);
            long facultyId = Long.parseLong(facultyStr);
            long profileId = Long.parseLong(profileStr);

            Application application = new Application();
            application.setOutOfCompetition(isOutCompetition);
            application.setDate(date);
            application.setFacultyId(facultyId);
            application.setProfileId(profileId);

            return application;
        } catch (NumberFormatException e) {
            throw new CommandBeanFactoryException("Couldn't build application bean", e);
        }
    }
}
