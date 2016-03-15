package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.Faculty;
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
import com.epam.finaltask.university.controller.util.bean.factory.impl.FacultyCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingFacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Update faculty data command.
 */
public class UpdateFacultyDataCommand implements Command {
    /**
     * Execute faculty update with faculty id.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            CommandBeanFactory<Faculty> factory = FacultyCommandBeanFactory.getInstance();
            Faculty faculty = factory.constructBean(request);

            String idStr = request.getParameter(RequestParameterName.FACULTY_ID);
            long id = Long.parseLong(idStr);
            faculty.setId(id);

            LockingFacultyService service = LockingFacultyService.getInstance();

            faculty = service.updateFaculty(faculty);

            if (faculty == null) {
                throw new InvalidDataException("Couldn't update faculty");
            }
            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);

            return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process faculty update command");
        }
    }
}
