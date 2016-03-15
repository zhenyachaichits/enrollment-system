package com.epam.finaltask.university.controller.command.impl.logic.deletion;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.concurrent.LockingApplicationService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Delete application command.
 */
public class DeleteApplicationCommand implements Command {
    /**
     *  Execute deletion of application by profile ID.
     *  Access is allowed for users with status: SUPPORT
     * @param request
     * @param response
     * @return support search page redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.SUPPORT);

            String idString = request.getParameter(RequestParameterName.PROFILE_ID);
            long profileId = Long.parseLong(idString);

            LockingApplicationService service = LockingApplicationService.getInstance();
            boolean isDeleted = service.deleteApplicationByProfileId(profileId);

            if (!isDeleted) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            return CommandName.GO_SUPPORT_SEARCH.getQueryString();

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
