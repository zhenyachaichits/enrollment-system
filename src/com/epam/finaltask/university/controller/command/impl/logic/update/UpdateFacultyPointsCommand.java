package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.service.ApplicationService;
import com.epam.finaltask.university.service.concurrent.LockingFacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 02.03.2016.
 */
public class UpdateFacultyPointsCommand implements Command {

    private static final boolean FREE_FORM = true;

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

            ApplicationService appService = ApplicationService.getInstance();
            int freePoint = appService.findPassingPoints(facultyId, FREE_FORM, freeQuota);
            int paidPoint = appService.findPassingPoints(facultyId, !FREE_FORM, paidQuota);

            LockingFacultyService service = LockingFacultyService.getInstance();

            boolean isUpdated = service.updatePoints(facultyId, freePoint, paidPoint);

            if (!isUpdated) {
                throw new InvalidDataException("Couldn't update faculty points");
            }

            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);
            return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;

        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process faculty update command");
        }
    }
}
