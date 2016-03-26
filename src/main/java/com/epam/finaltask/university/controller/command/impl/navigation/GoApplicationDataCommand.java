package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.ApplicationService;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.StudentService;
import com.epam.finaltask.university.service.TermsService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Go application data command.
 */
public class GoApplicationDataCommand implements Command {
    /**
     * Execute navigation to application data
     * Access is allowed for users with status: STUDENT
     *
     * @param request  the request
     * @param response the response
     * @return application data page name
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.STUDENT);

            String email = (String) session.getAttribute(SessionParameterName.EMAIL);

            StudentService studentService = StudentService.getInstance();
            Student student = studentService.getStudentByEmail(email);

            if (student != null) {
                long profileId = student.getProfile().getId();

                long facultyId = student.getProfile().getFacultyId();
                FacultyService facultyService = FacultyService.getInstance();
                Faculty faculty = facultyService.findFacultyById(facultyId);

                ApplicationService applicationService = ApplicationService.getInstance();
                Application application = applicationService.findApplicationByProfileId(profileId);

                TermsService termsService = TermsService.getInstance();
                Terms terms = termsService.getTermsByFacultyId(student.getProfile().getFacultyId());

                request.setAttribute(RequestParameterName.STUDENT, student);
                request.setAttribute(RequestParameterName.FACULTY, faculty);
                request.setAttribute(RequestParameterName.TERMS, terms);
                request.setAttribute(RequestParameterName.APPLICATION, application);

            } else {
                throw new InvalidDataException("Couldn't find student or application from session");
            }

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));
            return JspPageName.APPLICATION_DATA_PAGE;
        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
