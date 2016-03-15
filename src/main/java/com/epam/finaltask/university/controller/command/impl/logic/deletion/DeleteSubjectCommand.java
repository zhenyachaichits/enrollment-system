package com.epam.finaltask.university.controller.command.impl.logic.deletion;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.concurrent.LockingSubjectService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Delete subject command.
 */
public class DeleteSubjectCommand implements Command {
    /**
     * Execute subject deletion by subject ID.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return subject management page redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.SUBJECT_ID);
            long subjectId = Long.parseLong(idStr);

            LockingSubjectService service = LockingSubjectService.getInstance();
            boolean isDeleted = service.deleteSubject(subjectId);

            if (!isDeleted) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            return CommandName.GO_SUBJECT_MANAGEMENT.getQueryString();

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
