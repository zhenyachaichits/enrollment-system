package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 07.02.16.
 */
public class SetLocaleCommand implements Command {

    private static final String LOCALE_ATTR = "locale";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        // TODO: fix bug on statistics page

        String locale = request.getParameter(RequestParameterName.LOCALE);

        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE_ATTR, locale);

        return (String) request.getAttribute(RequestParameterName.FORWARD_URI);
    }
}
