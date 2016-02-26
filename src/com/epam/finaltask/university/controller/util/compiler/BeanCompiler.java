package com.epam.finaltask.university.controller.util.compiler;

import com.epam.finaltask.university.controller.util.compiler.exception.BeanCompilerException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public interface BeanCompiler<T> {
    T compile(HttpServletRequest request) throws BeanCompilerException;
}
