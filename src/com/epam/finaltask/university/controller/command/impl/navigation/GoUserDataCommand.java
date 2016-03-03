package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Go user data command.
 */
public class GoUserDataCommand implements Command {
    /**
     * Go user fata.
     * Access is allowed for users with status: ADMIN
     *
     * @param request  the request
     * @param response the response
     * @return Udate
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.manageAccess(session, UserType.ADMIN);

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));
            String idStr = request.getParameter(RequestParameterName.USER_ID);
            long userId = Long.parseLong(idStr);

            UserService userService = UserService.getInstance();
            User user = userService.getUserById(userId);

            if (user != null) {
                request.setAttribute(RequestParameterName.USER, user);
            } else {
                throw new InvalidDataException("Couldn't find this user");
            }

            return JspPageName.USER_DATA_PAGE;
        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
