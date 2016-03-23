package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Command Authenticate with cookie.
 */
public class AuthenticateWithCookieCommand implements Command {

    private static final Logger LOG = LogManager.getLogger(AuthenticateWithCookieCommand.class.getClass());

    private static final String COOKIE_ID_NAME = "university_user_id";

    /**
     * Execute authentication with cookie command
     * @param request
     * @param response
     * @return constant from class AjaxResponseValue
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 1) {
            HttpSession session = request.getSession(true);

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_ID_NAME)) {
                    addFoundToSession(cookie, session);

                    return CommandName.GO_HOME.getQueryString();
                }
            }
        }
        return AjaxResponseValue.NEGATIVE.toString();
    }


    /**
     * Add found by ID from cookie to current session
     * @param cookie
     * @param session
     * @throws CommandException
     */
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
                LOG.error("Invalid user ID was found in cookies. Couldn't authenticate user with cookies");
            }

        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Couldn't add user from cookie to session");
        }

    }
}
