package com.epam.finaltask.university.controller.command.impl.logic.creation;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;
import com.epam.finaltask.university.controller.util.bean.factory.impl.SubjectCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingSubjectService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Create subject command.
 */
public class CreateSubjectCommand implements Command {
    /**
     *  Execute creation of new subject
     * @param request
     * @param response
     * @return subject management page name redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.ADMIN);

            CommandBeanFactory<Subject> factory = SubjectCommandBeanFactory.getInstance();
            Subject subject = factory.constructBean(request);

            LockingSubjectService service = LockingSubjectService.getInstance();
            subject = service.createSubject(subject);
            if (subject == null) {
                throw new InvalidDataException("Invalid faculty data");
            }

            return CommandName.GO_SUBJECT_MANAGEMENT.getQueryString();

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute faculty creation command", e);
        }
    }
}
