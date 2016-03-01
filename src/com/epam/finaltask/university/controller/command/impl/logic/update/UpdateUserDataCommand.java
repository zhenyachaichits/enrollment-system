package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.User;
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
import com.epam.finaltask.university.controller.util.bean.factory.impl.UserCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingUserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 29.02.2016.
 */
public class UpdateUserDataCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            long currentUserId = (long) session.getAttribute(SessionParameterName.UID);
            AccessManager.manageAccess(session, UserType.ADMIN);

            LockingUserService service = LockingUserService.getInstance();

            CommandBeanFactory<User> factory = UserCommandBeanFactory.getInstance();
            User user = factory.constructBean(request);

            String userStr = request.getParameter(RequestParameterName.USER_ID);
            long userId = Long.parseLong(userStr);
            user.setId(userId);

            user = service.updateUser(user);

            if (user == null) {
                throw new InvalidDataException("Couldn't update profile");
            }

            if (currentUserId == userId) {
                return CommandName.LOG_OUT.getQueryString();
            } else {
                String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);
                return currentQuery == null ? CommandName.GO_USER_MANAGEMENT.getQueryString() : currentQuery;
            }

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
