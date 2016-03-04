package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.UrlBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Go home command.
 */
public class GoHomeCommand implements Command {
    /**
     *
     * @param request  the request
     * @param response the response
     * @return home page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

        return JspPageName.INDEX;
    }
}
