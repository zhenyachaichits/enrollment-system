package com.epam.finaltask.university.controller.util.compiler.impl;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.compiler.BeanCompiler;
import com.epam.finaltask.university.controller.util.compiler.exception.BeanCompilerException;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class ApplicationCompiler implements BeanCompiler<Application> {

    private ApplicationCompiler() {
    }

    public static class ApplicationCompilerHolder {
        public static final ApplicationCompiler INSTANCE = new ApplicationCompiler();
    }

    public static ApplicationCompiler getInstance() {
        return ApplicationCompilerHolder.INSTANCE;
    }

    private static final String CHECKED = "on";

    @Override
    public Application compile(HttpServletRequest request) throws BeanCompilerException {
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
    }
}
