package com.epam.finaltask.university.controller.util.bean.factory.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class UserCommandBeanFactory implements CommandBeanFactory<User> {

    private UserCommandBeanFactory() {
    }

    public static class UserCompilerHolder {
        public static final UserCommandBeanFactory INSTANCE = new UserCommandBeanFactory();
    }

    public static UserCommandBeanFactory getInstance() {
        return UserCompilerHolder.INSTANCE;
    }

    @Override
    public User compile(HttpServletRequest request) {
        String email = request.getParameter(RequestParameterName.EMAIL);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
