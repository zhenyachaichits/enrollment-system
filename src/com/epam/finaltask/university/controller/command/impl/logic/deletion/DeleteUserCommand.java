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
 * Created by Zheny Chaichits on 01.03.2016.
 */
public class DeleteUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            long currentUserId = (long) session.getAttribute(SessionParameterName.UID);
            AccessManager.manageAccess(session, UserType.ADMIN);

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
