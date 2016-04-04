package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.SubjectService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckSubjectNameExistenceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String subjectName = request.getParameter(RequestParameterName.SUBJECT_NAME);

            SubjectService service = SubjectService.getInstance();

            if (service.checkSubjectExistence(subjectName)) {
                return AjaxResponseValue.POSITIVE.toString();
            } else {
                return AjaxResponseValue.NEGATIVE.toString();
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute subject name checking command", e);
        }
    }
}
