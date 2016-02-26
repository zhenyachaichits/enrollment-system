package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.compiler.UrlCompiler;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class GoStatisticsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            FacultyService service = FacultyService.getInstance();
            List<Faculty> faculties = service.getAllFaculties();

            request.setAttribute(RequestParameterName.FACULTIES, faculties);

            HttpSession session = request.getSession(true);
            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlCompiler.compile(request));

            return JspPageName.STATICTICS_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
