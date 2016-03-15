package com.epam.finaltask.university.controller.command.impl.logic.deletion;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.concurrent.LockingStudentService;
import com.epam.finaltask.university.service.concurrent.LockingUserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Delete user command.
 */
public class DeleteUserCommand implements Command {
    /**
     * Execute deletion of user account by user ID. In case user is trying to delete his/her
     * own account, account will be deleted and user will be redirected to log out command .
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return log out command or user management page redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            long currentUserId = (long) session.getAttribute(SessionParameterName.UID);
            AccessManager.provideAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.USER_ID);
            long userId = Long.parseLong(idStr);
            String role = request.getParameter(RequestParameterName.ROLE);
            UserType userType = UserType.valueOf(role);

            boolean isDeleted;
            if (userType == UserType.STUDENT) {
                LockingStudentService service = LockingStudentService.getInstance();
                isDeleted = service.deleteAccount(userId);
            } else {
                LockingUserService service = LockingUserService.getInstance();
                isDeleted = service.deleteUser(userId);
            }

            if (!isDeleted) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            if (currentUserId == userId) {
                return CommandName.LOG_OUT.getQueryString();
            } else {
                return CommandName.GO_USER_MANAGEMENT.getQueryString();
            }

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
