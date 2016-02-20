package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidSessionException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.UrlCompiler;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.impl.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 19.02.2016.
 */
public class GoProfileCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlCompiler.compile(request));
                String email = (String) session.getAttribute(SessionParameterName.EMAIL);

                StudentService service = StudentService.getInstance();
                Student student = service.getStudentByEmail(email);

                if (student != null) {
                    request.setAttribute(RequestParameterName.STUDENT, student);
                } else {
                    throw new InvalidDataException("Couldn't find student from session");
                }

            } else {
                throw new InvalidSessionException("User session is invalid");
            }

            return JspPageName.PROFILE_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
