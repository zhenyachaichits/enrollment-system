package com.epam.finaltask.university.filter;

import com.epam.finaltask.university.controller.RequestParameterName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zheny Chaichits on 06.02.16.
 */
public class ActionFilter implements Filter {

    private static final String PATH_PARAMETER = "contentPath";
    private static final String SERVLET_PATTERN_PARAMETER = "servletPattern";

    private String contentPath;
    private String servletPattern;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contentPath = filterConfig.getInitParameter(PATH_PARAMETER);
        servletPattern = filterConfig.getInitParameter(SERVLET_PATTERN_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        String commandName = servletRequest.getParameter(RequestParameterName.COMMAND_NAME);

        if (!path.startsWith(contentPath) && commandName != null) {
            servletRequest.getRequestDispatcher(servletPattern).forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
