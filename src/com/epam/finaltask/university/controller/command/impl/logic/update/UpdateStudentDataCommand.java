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
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class UpdateStudentDataCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.SUPPORT);

            LockingProfileService service = LockingProfileService.getInstance();
            CommandBeanFactory<Profile> compiler = ProfileCommandBeanFactory.getInstance();
            Profile profile = compiler.compile(request);

            String userStr = request.getParameter(RequestParameterName.USER_ID);
            long userId = Long.parseLong(userStr);
            profile.setUserId(userId);

            profile = service.updateProfile(profile);

            if (profile == null) {
                throw new InvalidDataException("Couldn't update profile");
            }

            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);

            return currentQuery == null ? CommandName.GO_APPLY_FORM.getQueryString() : currentQuery;

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
