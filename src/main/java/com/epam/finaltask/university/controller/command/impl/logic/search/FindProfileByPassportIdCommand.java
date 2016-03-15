package com.epam.finaltask.university.controller.command.impl.logic.search;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Find profile by passport id command.
 */
public class FindProfileByPassportIdCommand implements Command {

    private static final int LIST_SIZE = 1;

    /**
     * Execute searching non-applied profiles by ID.
     * Access is allowed for users with status: SUPPORT
     * @param request
     * @param response
     * @return support search page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String passportId = request.getParameter(RequestParameterName.PASSPORT_ID);
            HttpSession session = request.getSession(false);

            AccessManager.provideAccess(session, UserType.SUPPORT);

            ProfileService service = ProfileService.getInstance();
            Profile profile = service.findProfileByPassportId(passportId);

            List<Profile> profileList = new ArrayList<>(LIST_SIZE);
            if (profile != null) {
                profileList.add(profile);
            }
            request.setAttribute(RequestParameterName.PROFILES, profileList);
            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

            return JspPageName.SUPPORT_SEARCH_PAGE;

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute profile searching command", e);
        }
    }
}
