package com.epam.finaltask.university.controller.command.impl.logic.search;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.Paginator;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Find all applied command.
 */
public class FindAllAppliedCommand implements Command {
    /**
     * Execute finding of all applied profiles with pagination.
     * Access is allowed for users with status: SUPPORT
     * @param request
     * @param response
     * @return support search page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.provideAccess(session, UserType.SUPPORT);

            int currentPage = Paginator.DEFAULT_PAGE;
            if(request.getParameter(RequestParameterName.CURRENT_PAGE) != null) {
                currentPage = Integer.parseInt(request.getParameter(RequestParameterName.CURRENT_PAGE));
            }

            int offset = Paginator.calculateOffset(currentPage);
            ProfileService service = ProfileService.getInstance();
            List<Profile> profileList = service.getAllApplied(offset, Paginator.RECORDS_ON_PAGE);

            int recordsNumber = service.getCurrentRecordsCount();
            int pagesNumber = Paginator.calculatePagesNumber(recordsNumber);

            request.setAttribute(RequestParameterName.PROFILES, profileList);
            request.setAttribute(RequestParameterName.CURRENT_PAGE, currentPage);
            request.setAttribute(RequestParameterName.PAGES_NUMBER, pagesNumber);

            String currentQuery = UrlBuilder.build(request);
            request.setAttribute(RequestParameterName.COMMAND_NAME, currentQuery);
            session.setAttribute(SessionParameterName.CURRENT_PAGE, currentQuery);

            return JspPageName.SUPPORT_SEARCH_PAGE;

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute profile searching command", e);
        }
    }
}
