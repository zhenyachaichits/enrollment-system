package com.epam.finaltask.university.controller.command.impl.logic.search;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.Paginator;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Find users by role command.
 */
public class FindUsersByRoleCommand implements Command {
    /**
     * Execute searching users by selected role with pagination.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return user management page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String roleStr = request.getParameter(RequestParameterName.ROLE);
            UserType userType = UserType.valueOf(roleStr);
            HttpSession session = request.getSession(false);

            AccessManager.provideAccess(session, UserType.ADMIN);

            int currentPage = Paginator.DEFAULT_PAGE;
            if (request.getParameter(RequestParameterName.CURRENT_PAGE) != null) {
                currentPage = Integer.parseInt(request.getParameter(RequestParameterName.CURRENT_PAGE));
            }

            int offset = Paginator.calculateOffset(currentPage);

            UserService service = UserService.getInstance();
            List<User> users = service.findAllUsersByRole(userType, offset,
                    Paginator.RECORDS_ON_PAGE);

            int recordsNumber = service.getCurrentRecordsCount();
            int pagesNumber = Paginator.calculatePagesNumber(recordsNumber);

            request.setAttribute(RequestParameterName.USERS, users);
            request.setAttribute(RequestParameterName.CURRENT_PAGE, currentPage);
            request.setAttribute(RequestParameterName.PAGES_NUMBER, pagesNumber);

            String currentQuery = UrlBuilder.build(request);
            request.setAttribute(RequestParameterName.COMMAND_NAME, currentQuery);
            session.setAttribute(SessionParameterName.CURRENT_PAGE, currentQuery);

            return JspPageName.USER_MANAGEMENT_PAGE;

        } catch (IllegalArgumentException | ServiceException e) {
            throw new CommandException("Couldn't execute profile searching command", e);
        }
    }
}
