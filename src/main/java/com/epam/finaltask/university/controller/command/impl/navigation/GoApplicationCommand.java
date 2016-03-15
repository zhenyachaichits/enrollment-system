package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.ApplicationService;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Go application command.
 */
public class GoApplicationCommand implements Command {
    /**
     * Execute navigation to application page.
     * Access is allowed for users with status: SUPPORT
     *
     * @param request  the request
     * @param response the response
     * @return application page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.denyAccess(session, UserType.STUDENT);

            String profileStr = request.getParameter(RequestParameterName.PROFILE_ID);
            long profileId = Long.parseLong(profileStr);

            ApplicationService applicationService = ApplicationService.getInstance();
            Application application = applicationService.findApplicationByProfileId(profileId);

            ProfileService profileService = ProfileService.getInstance();
            Profile profile = profileService.findProfileById(profileId);

            FacultyService facultyService = FacultyService.getInstance();
            List<Faculty> faculties = facultyService.getAllFaculties();

            request.setAttribute(RequestParameterName.FACULTIES, faculties);

            if (profile != null && application != null) {
                request.setAttribute(RequestParameterName.PROFILE, profile);
                request.setAttribute(RequestParameterName.APPLICATION, application);
            } else {
                throw new InvalidDataException("Couldn't find profile or application session");
            }

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

            return JspPageName.APPLICATION_PAGE;
        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
