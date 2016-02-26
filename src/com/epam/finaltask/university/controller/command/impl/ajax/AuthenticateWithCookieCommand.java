package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class AuthenticateWithCookieCommand implements Command {

    private static final String COOKIE_ID_NAME = "university_user_id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 1) {
            HttpSession session = request.getSession(true);

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_ID_NAME)) {
                    addFoundToSession(cookie, session);

                    return AjaxResponseValue.POSITIVE;
                }
            }
        }
        return AjaxResponseValue.NEGATIVE;
    }

    private void addFoundToSession(Cookie cookie, HttpSession session) throws CommandException {
        try {
            UserService service = UserService.getInstance();
            String id = cookie.getValue();

            User user = service.getUserById(Long.parseLong(id));
            if (user != null) {
                session.setAttribute(SessionParameterName.EMAIL, user.getEmail());
                session.setAttribute(SessionParameterName.ROLE, user.getRole());
                session.setAttribute(SessionParameterName.UID, user.getId());
            } else {
                // TODO: 11.02.2016 maybe add logging
            }

        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Couldn't add user from cookie to session");
        }

    }
}
