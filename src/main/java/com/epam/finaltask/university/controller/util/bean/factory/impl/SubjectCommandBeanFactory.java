package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;


/**
 * Subject command bean factory.
 */
public class SubjectCommandBeanFactory implements CommandBeanFactory<Subject> {

    private SubjectCommandBeanFactory() {
    }

    public static class SubjectBeanFactoryHolder {
        public static final SubjectCommandBeanFactory INSTANCE = new SubjectCommandBeanFactory();
    }

    public static SubjectCommandBeanFactory getInstance() {
        return SubjectBeanFactoryHolder.INSTANCE;
    }

    /**
     * Creates new subject bean from request parameters
     *
     * @param request
     * @return new subject reference
     * @throws CommandBeanFactoryException
     */
    @Override
    public Subject constructBean(HttpServletRequest request) throws CommandBeanFactoryException {
        try {
            String name = request.getParameter(RequestParameterName.SUBJECT_NAME);
            String minPointStr = request.getParameter(RequestParameterName.MIN_POINT);

            int minPoint = Integer.parseInt(minPointStr);

            Subject subject = new Subject();
            subject.setName(name);
            subject.setMinPoint(minPoint);

            return subject;
        } catch (NumberFormatException e) {
            throw new CommandBeanFactoryException("Couldn't build subject bean", e);
        }
    }
}
