package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.service.SubjectService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;


/**
 * Get faculty subjects command.
 */
public class GetFacultySubjectsCommand implements Command {

    private static final String CONTENT_TYPE = "application/xml";

    /**
     * Execute getting matching subjects for selected faculty with AJAX
     *
     * @param request
     * @param response
     * @return JSP page generating XML name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {

            String facultyId = request.getParameter(RequestParameterName.FACULTY_ID);
            long id = Long.parseLong(facultyId);

            SubjectService service = SubjectService.getInstance();
            List<Subject> subjects = service.getSubjectsByFacultyId(id);

            request.setAttribute(RequestParameterName.SUBJECTS, subjects);
            response.setContentType(CONTENT_TYPE);

            return JspPageName.SUBJECTS_XML;

        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Couldn't execute subject finding command", e);
        }
    }
}
