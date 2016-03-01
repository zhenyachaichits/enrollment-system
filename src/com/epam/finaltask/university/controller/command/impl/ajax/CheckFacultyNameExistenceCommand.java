package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.FacultyService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zheny Chaichits on 01.03.2016.
 */
public class CheckFacultyNameExistenceCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String facultyName = request.getParameter(RequestParameterName.FACULTY_NAME);

            FacultyService service = FacultyService.getInstance();

            if (service.checkFacultyExistence(facultyName)) {
                return AjaxResponseValue.POSITIVE;
            } else {
                return AjaxResponseValue.NEGATIVE;
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute faculty name checking command", e);
        }
    }
}
