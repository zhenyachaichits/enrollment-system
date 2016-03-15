package com.epam.finaltask.university.controller.command.impl.logic.deletion;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.concurrent.LockingFacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Delete faculty command.
 */
public class DeleteFacultyCommand implements Command {
    /**
     * Execute deletion of existing faculty by faculty ID .
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return faculty management page redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.FACULTY_ID);
            long facultyId = Long.parseLong(idStr);

            LockingFacultyService service = LockingFacultyService.getInstance();
            boolean isDeleted = service.deleteFaculty(facultyId);

            if (!isDeleted) {
                throw new InvalidDataException("Invalid faculty data.");
            }

            return CommandName.GO_FACULTY_MANAGEMENT.getQueryString();

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute faculty deleting command", e);
        }
    }
}
