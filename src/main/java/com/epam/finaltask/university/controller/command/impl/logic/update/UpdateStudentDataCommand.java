package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;
import com.epam.finaltask.university.controller.util.bean.factory.impl.ProfileCommandBeanFactory;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.concurrent.LockingProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Update student data command.
 */
public class UpdateStudentDataCommand implements Command {
    /**
     * Execute student data updating.
     * Access is allowed for users with status: SUPPORT
     * @param request
     * @param response
     * @return home page or previous page redirect query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.SUPPORT);

            LockingProfileService service = LockingProfileService.getInstance();
            CommandBeanFactory<Profile> compiler = ProfileCommandBeanFactory.getInstance();
            Profile profile = compiler.constructBean(request);

            String userStr = request.getParameter(RequestParameterName.USER_ID);
            long userId = Long.parseLong(userStr);
            profile.setUserId(userId);

            profile = service.updateProfile(profile);

            if (profile == null) {
                throw new InvalidDataException("Couldn't update profile");
            }

            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);

            return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
