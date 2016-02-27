package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.UrlCompiler;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Zheny Chaichits on 19.02.2016.
 */
public class GoProfileCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.manageAccess(session, UserType.STUDENT);

            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlCompiler.compile(request));
            String email = (String) session.getAttribute(SessionParameterName.EMAIL);

            FacultyService facultyService = FacultyService.getInstance();
            List<Faculty> faculties = facultyService.getAllFaculties();

            request.setAttribute(RequestParameterName.FACULTIES, faculties);

            StudentService studentService = StudentService.getInstance();
            Student student = studentService.getStudentByEmail(email);

            if (student != null) {
                request.setAttribute(RequestParameterName.STUDENT, student);
            } else {
                throw new InvalidDataException("Couldn't find student from session");
            }

            return JspPageName.PROFILE_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
