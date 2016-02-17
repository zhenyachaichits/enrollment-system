package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Zheny Chaichits on 07.02.16.
 */
public class GoSignUpCommand implements Command {

    private static final String FACULTIES_ATTR = "faculties";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            FacultyService service = FacultyService.getInstance();
            List<Faculty> faculties = service.getAllFaculties();

            request.setAttribute(FACULTIES_ATTR, faculties);

            return JspPageName.SIGN_UP_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute navigation command", e);
        }
    }
}
