package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.Subject;
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
import com.epam.finaltask.university.controller.util.bean.factory.impl.SubjectCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingSubjectService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Update subject data command.
 */
public class UpdateSubjectDataCommand implements Command {
    /**
     * Execute subject data update by subject ID.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return home page or previous page redirect query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            CommandBeanFactory<Subject> factory = SubjectCommandBeanFactory.getInstance();
            Subject subject = factory.constructBean(request);

            String idStr = request.getParameter(RequestParameterName.SUBJECT_ID);
            long id = Long.parseLong(idStr);
            subject.setId(id);

            LockingSubjectService service = LockingSubjectService.getInstance();

            subject = service.updateSubject(subject);

            if (subject == null) {
                throw new InvalidDataException("Couldn't update profile");
            }

            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);
            return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
