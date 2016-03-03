package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.TermsService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Go facuty data command.
 */
public class GoFacultyDataCommand implements Command {
    /**
     * Execute navigation to faculty data page
     * Access is allowed for users with status: ADMIN;
     *
     * @param request  the request
     * @param response the response
     * @return faculty data page
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.ADMIN);

            String idStr = request.getParameter(RequestParameterName.FACULTY_ID);
            long facultyId = Long.parseLong(idStr);

            FacultyService facultyService = FacultyService.getInstance();
            Faculty faculty = facultyService.findFacultyById(facultyId);

            TermsService termsService = TermsService.getInstance();
            List<Terms> termsList = termsService.getAllTerms();

            request.setAttribute(RequestParameterName.FACULTY, faculty);
            request.setAttribute(RequestParameterName.TERMS_LIST, termsList);

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

            return JspPageName.FACULTY_DATA_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
