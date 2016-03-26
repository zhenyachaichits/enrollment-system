package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command Check account.
 */
public class AuthenticateUserCommand implements Command {

    private static final String ID_COOKIE = "university_user_id";
    /**
     * Constant equals 30 days
     */
    private static final int COOKIE_AGE = 2_592_000;
    private static final String COOKIE_PATH = "/";

    /**
     * Execute checking user data validity befote authentication
     * @param request
     * @param response
     * @return constant from class AjaxResponseValue
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String email = request.getParameter(RequestParameterName.EMAIL);
            String password = request.getParameter(RequestParameterName.PASSWORD);
            String rememberedStr = request.getParameter(RequestParameterName.REMEMBERED);

            boolean isRemembered = Boolean.parseBoolean(rememberedStr);

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            UserService service = UserService.getInstance();
            user = service.authenticateUser(user);

            if (user != null) {
                if (isRemembered) {
                    saveInCookies(user, response);
                }
                saveInSession(user, request);

                return CommandName.GO_HOME.getQueryString();
            } else {
                return AjaxResponseValue.NEGATIVE.toString();
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute pre-authentication command", e);
        }
    }

    /**
     * Saves user data in cookies
     * @param user
     * @param response
     */
    private void saveInCookies(User user, HttpServletResponse response) {
        String id = String.valueOf(user.getId());

        Cookie idCookie = new Cookie(ID_COOKIE, id);

        idCookie.setMaxAge(COOKIE_AGE);
        idCookie.setPath(COOKIE_PATH);

        response.addCookie(idCookie);
    }

    private void saveInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        session.setAttribute(SessionParameterName.EMAIL, user.getEmail());
        session.setAttribute(SessionParameterName.ROLE, user.getRole());
        session.setAttribute(SessionParameterName.UID, user.getId());
    }
}
