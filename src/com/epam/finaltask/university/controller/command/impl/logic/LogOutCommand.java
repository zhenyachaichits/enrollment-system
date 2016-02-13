package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 06.02.16.
 */
public class LogOutCommand implements Command {

    private static final String EMAIL_SESSION = "userEmail";
    private static final String ROLE_SESSION = "userRole";

    private static final String COOKIE_ID = "university_user_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        session.removeAttribute(EMAIL_SESSION);
        session.removeAttribute(ROLE_SESSION);

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

        return (String) request.getAttribute(RequestParameterName.FORWARD_URI);
    }
}
