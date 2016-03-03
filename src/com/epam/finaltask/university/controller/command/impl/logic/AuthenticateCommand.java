package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Authenticate command.
 */
public class AuthenticateCommand implements Command {

    private static final String ID_COOKIE = "university_user_id";
    /**
     * Constant equals 30 days
     */
    private static final int COOKIE_AGE = 2_592_000;
    private static final String COOKIE_PATH = "/";

    private static final String REMEMBERED_CHECKED = "on";

    /**
     * Execute user authentication. If needed, current user data will be saved in cookies.
     * By default, user data will be saved in current session.
     * @param request
     * @param response
     * @return home page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String email = request.getParameter(RequestParameterName.EMAIL);
            String password = request.getParameter(RequestParameterName.PASSWORD);
            String rememberedStr = request.getParameter(RequestParameterName.REMEMBERED);

            boolean isRemembered = REMEMBERED_CHECKED.equals(rememberedStr);

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
            } else {
                throw new InvalidDataException("Invalid user data. Couldn't sign in");
            }

            return JspPageName.INDEX;

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute authentication command", e);
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

    /**
     * Saves user data in session
     * @param user
     * @param request
     */
    private void saveInSession(User user, HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        session.setAttribute(SessionParameterName.EMAIL, user.getEmail());
        session.setAttribute(SessionParameterName.ROLE, user.getRole());
        session.setAttribute(SessionParameterName.UID, user.getId());
    }
}
