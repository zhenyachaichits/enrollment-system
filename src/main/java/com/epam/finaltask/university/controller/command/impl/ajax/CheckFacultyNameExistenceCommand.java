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
 * Check faculty name existence command.
 */
public class CheckFacultyNameExistenceCommand implements Command{
    /**
     * Execute AJAX faculty name checking to restrict creating faculty with existing name
     * @param request
     * @param response
     * @return constant from class AjaxResponseValue
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String facultyName = request.getParameter(RequestParameterName.FACULTY_NAME);

            FacultyService service = FacultyService.getInstance();

            if (service.checkFacultyExistence(facultyName)) {
                return AjaxResponseValue.POSITIVE.toString();
            } else {
                return AjaxResponseValue.NEGATIVE.toString();
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute faculty name checking command", e);
        }
    }
}
