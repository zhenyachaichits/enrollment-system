package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.service.impl.SubjectService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Zheny Chaichits on 15.02.2016.
 */
public class GetFacultySubjectsCommand implements Command {

    private static final String SUBJECTS_ATTR = "subjects";
    private static final String CONTENT_TYPE = "application/xml";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String facultyId = request.getParameter(RequestParameterName.FACULTY_ID);
            long id = Long.parseLong(facultyId);

            SubjectService service = SubjectService.getInstance();
            List<Subject> subjects = service.getSubjectsByFacultyId(id);

            request.setAttribute(SUBJECTS_ATTR, subjects);
            response.setContentType(CONTENT_TYPE);

            return JspPageName.SUBJECTS_XML;

        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Couldn't execute subject finding command", e);
        }
    }
}
