package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.UrlCompiler;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Zheny Chaichits on 22.02.2016.
 */
public class GoSupportSearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.manageAccess(session, UserType.SUPPORT);

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlCompiler.compile(request));

            ProfileService service = ProfileService.getInstance();
            List<Profile> profiles = service.getAllProfiles();

            request.setAttribute(RequestParameterName.PROFILES, profiles);

            return JspPageName.SUPPORT_SEARCH_PAGE;

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
