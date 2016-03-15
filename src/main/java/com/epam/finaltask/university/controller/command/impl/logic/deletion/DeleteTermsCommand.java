package com.epam.finaltask.university.controller.command.impl.logic.deletion;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.concurrent.LockingTermsService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Delete terms command.
 */
public class DeleteTermsCommand implements Command {
    /**
     * Execute terns deletion by terms ID.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return terms management page redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.TERMS_ID);
            long subjectId = Long.parseLong(idStr);

            LockingTermsService service = LockingTermsService.getInstance();
            boolean isDeleted = service.deleteTerms(subjectId);

            if (!isDeleted) {
                throw new InvalidDataException("Invalid terms data.");
            }

            return CommandName.GO_TERMS_MANAGEMENT.getQueryString();

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute terms deleting command", e);
        }
    }
}
