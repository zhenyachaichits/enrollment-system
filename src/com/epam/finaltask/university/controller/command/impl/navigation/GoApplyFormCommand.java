package com.epam.finaltask.university.controller.command.impl.navigation;

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
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Go apply form command.
 */
public class GoApplyFormCommand implements Command {
    /**
     * Execute navigation to apply form.
     *
     * @param request  the request
     * @param response the response
     * @return apply form page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.manageAccess(session, UserType.SUPPORT);

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

            String profileStr = request.getParameter(RequestParameterName.PROFILE_ID);
            long profileId = Long.parseLong(profileStr);

            ProfileService profileService = ProfileService.getInstance();
            Profile profile = profileService.findProfileById(profileId);

            FacultyService facultyService = FacultyService.getInstance();
            List<Faculty> faculties = facultyService.getAllFaculties();

            request.setAttribute(RequestParameterName.FACULTIES, faculties);

            if (profile != null) {
                request.setAttribute(RequestParameterName.PROFILE, profile);
            } else {
                throw new InvalidDataException("Couldn't find student from session");
            }

            return JspPageName.APPLY_FORM_PAGE;
        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
