package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 07.02.16.
 */
public class SetLocaleCommand implements Command {

    private static final String EMPTY_QUERY = "";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String locale = request.getParameter(RequestParameterName.LOCALE);

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionParameterName.LOCALE, locale);
        String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);

        return currentQuery == null ? EMPTY_QUERY : currentQuery;
    }
}
