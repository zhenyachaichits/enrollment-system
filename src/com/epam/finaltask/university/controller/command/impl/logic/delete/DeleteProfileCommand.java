package com.epam.finaltask.university.controller.command.impl.logic.delete;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.concurrent.LockingStudentService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 27.02.2016.
 */
public class DeleteProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.STUDENT);

            long userId = (Long) session.getAttribute(SessionParameterName.UID);

            LockingStudentService service = LockingStudentService.getInstance();
            boolean isDeleted = service.deleteAccount(userId);

            if (!isDeleted) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            return CommandName.LOG_OUT.getQueryString();

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
