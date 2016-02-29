package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;
import com.epam.finaltask.university.controller.util.bean.factory.impl.ApplicationCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingApplicationService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class CreateApplicationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.SUPPORT);

            CommandBeanFactory<Application> compiler = ApplicationCommandBeanFactory.getInstance();
            Application application = compiler.constructBean(request);

            LockingApplicationService service = LockingApplicationService.getInstance();
            application = service.createNewApplication(application);

            if (application == null) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            return CommandName.GO_SUPPORT_SEARCH.getQueryString();

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
