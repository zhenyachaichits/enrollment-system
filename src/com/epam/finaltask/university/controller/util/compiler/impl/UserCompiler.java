package com.epam.finaltask.university.controller.util.compiler.impl;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.util.compiler.BeanCompiler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class UserCompiler implements BeanCompiler<User> {

    private UserCompiler() {
    }

    public static class UserCompilerHolder {
        public static final UserCompiler INSTANCE = new UserCompiler();
    }

    public static UserCompiler getInstance() {
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
