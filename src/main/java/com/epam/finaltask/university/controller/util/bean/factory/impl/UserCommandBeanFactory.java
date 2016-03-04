package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;


/**
 * User command bean factory.
 */
public class UserCommandBeanFactory implements CommandBeanFactory<User> {

    private UserCommandBeanFactory() {
    }

    public static class UserBeanFactoryHolder {
        public static final UserCommandBeanFactory INSTANCE = new UserCommandBeanFactory();
    }

    public static UserCommandBeanFactory getInstance() {
        return UserBeanFactoryHolder.INSTANCE;
    }

    /**
     * Creates new user bean from request parameters
     *
     * @param request
     * @return new user reference
     * @throws CommandBeanFactoryException
     */
    @Override
    public User constructBean(HttpServletRequest request) throws CommandBeanFactoryException {
        try {
            String email = request.getParameter(RequestParameterName.EMAIL);

            String password = request.getParameter(RequestParameterName.PASSWORD);
            String role = request.getParameter(RequestParameterName.ROLE);

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            if (role != null) {
                UserType userType = UserType.valueOf(role);
                user.setRole(userType);
            }

            return user;
        } catch (IllegalArgumentException e) {
            throw new CommandBeanFactoryException("Couldn't build user bean", e);
        }
    }
}
