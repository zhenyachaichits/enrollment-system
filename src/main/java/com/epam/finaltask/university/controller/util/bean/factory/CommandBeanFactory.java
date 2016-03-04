package com.epam.finaltask.university.controller.util.bean.factory;

import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;

import javax.servlet.http.HttpServletRequest;


/**
 * The interface Command bean factory.
 *
 * @param <T> the type parameter
 */
public interface CommandBeanFactory<T> {
    /**
     * Construct bean t.
     *
     * @param request the request
     * @return the new t
     * @throws CommandBeanFactoryException the command bean factory exception
     */
    T constructBean(HttpServletRequest request) throws CommandBeanFactoryException;
}
