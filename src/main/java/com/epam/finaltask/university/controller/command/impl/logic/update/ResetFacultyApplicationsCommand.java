package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
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
 * Created by Zheny Chaichits on 27.03.2016.
 */
public class ResetFacultyApplicationsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.FACULTY_ID);
            long facultyId = Long.parseLong(idStr);

            LockingApplicationService appService = LockingApplicationService.getInstance();
            appService.resetFacultyApplications(facultyId);

            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);
            return currentQuery == null ? CommandName.GO_FACULTY_DATA.getQueryString() : currentQuery;

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
