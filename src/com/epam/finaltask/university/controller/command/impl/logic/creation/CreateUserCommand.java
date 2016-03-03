package com.epam.finaltask.university.controller.command.impl.logic.creation;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
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
 * Create user command.
 */
public class CreateUserCommand implements Command {
    /**
     * Execute new user creation.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return user management page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.ADMIN);

            CommandBeanFactory<User> userFactory = UserCommandBeanFactory.getInstance();

            User user = userFactory.constructBean(request);

            LockingUserService service = LockingUserService.getInstance();

            user = service.createUser(user);
            if (user == null) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            return CommandName.GO_USER_MANAGEMENT.getQueryString();

        } catch (CommandBeanFactoryException | ServiceException e) {
            throw new CommandException("Couldn't execute authentication command", e);
        }
    }
}
