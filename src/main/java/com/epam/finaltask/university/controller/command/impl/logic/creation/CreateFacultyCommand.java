package com.epam.finaltask.university.controller.command.impl.logic.creation;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;
import com.epam.finaltask.university.controller.util.bean.factory.impl.FacultyCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingFacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Create faculty command.
 */
public class CreateFacultyCommand implements Command {
    /**
     * Execute new faculty creation
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return faculty management page name redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            CommandBeanFactory<Faculty> factory = FacultyCommandBeanFactory.getInstance();
            Faculty faculty = factory.constructBean(request);

            LockingFacultyService service = LockingFacultyService.getInstance();
            faculty = service.createFaculty(faculty);
            if (faculty == null) {
                throw new InvalidDataException("Invalid faculty data");
            }

            return CommandName.GO_FACULTY_MANAGEMENT.getQueryString();

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute faculty creation command", e);
        }
    }
}
