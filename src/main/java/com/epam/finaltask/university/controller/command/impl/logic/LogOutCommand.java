package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Log out command.
 */
public class LogOutCommand implements Command {

    private static final String COOKIE_ID = "university_user_id";

    /**
     * Execute logging out: deletion data from session and cookies;
     * Access is allowed all users.
     *
     * @param request  the request
     * @param response the response
     * @return home page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        //session.invalidate();

        session.removeAttribute(SessionParameterName.EMAIL);
        session.removeAttribute(SessionParameterName.ROLE);
        session.removeAttribute(SessionParameterName.CURRENT_PAGE);

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            String name = cookie.getName();
            if (name.equals(COOKIE_ID)) {
                cookie.setMaxAge(0);
                cookie.setValue(null);
                response.addCookie(cookie);
            }
        }

        return JspPageName.INDEX;
    }
}
