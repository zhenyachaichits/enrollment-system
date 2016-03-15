package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Go support search command.
 */
public class GoSupportSearchCommand implements Command {
    /**
     * execute navigation to supportSearch
     *
     * @param request  the request
     * @param response the response
     * @return support search page
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);

        AccessManager.provideAccess(session, UserType.SUPPORT);

        session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

        return JspPageName.SUPPORT_SEARCH_PAGE;
    }
}
