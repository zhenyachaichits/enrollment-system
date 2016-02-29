package com.epam.finaltask.university.controller.util.bean.factory;

import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public interface CommandBeanFactory<T> {
    T constructBean(HttpServletRequest request) throws CommandBeanFactoryException;
}
