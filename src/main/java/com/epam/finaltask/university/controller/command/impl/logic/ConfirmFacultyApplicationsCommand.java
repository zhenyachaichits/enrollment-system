package com.epam.finaltask.university.controller.command.impl.logic;

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
 * Confirm faculty applications command.
 */
public class ConfirmFacultyApplicationsCommand implements Command {

    private static final boolean FREE_FORM = true;

    /**
     * Execute faculty applications confirmation.
     * Access is allowed for users with status: ADMIN
     *
     * @param request  the request
     * @param response the response
     * @return current page or home page redirect query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.FACULTY_ID);
            String freeQuotaStr = request.getParameter(RequestParameterName.FREE_QUOTA);
            String paidQuotaStr = request.getParameter(RequestParameterName.PAID_QUOTA);

            long facultyId = Long.parseLong(idStr);
            int freeQuota = Integer.parseInt(freeQuotaStr);
            int paidQuota = Integer.parseInt(paidQuotaStr);

            LockingApplicationService appService = LockingApplicationService.getInstance();
            boolean isFreeConfirmed = appService.confirmFacultyApplications(facultyId, FREE_FORM, freeQuota);
            boolean isPaidConfirmed = appService.confirmFacultyApplications(facultyId, !FREE_FORM, paidQuota);

            if (!isFreeConfirmed || !isPaidConfirmed) {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);
            return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute application confirming command", e);
        }
    }
}
