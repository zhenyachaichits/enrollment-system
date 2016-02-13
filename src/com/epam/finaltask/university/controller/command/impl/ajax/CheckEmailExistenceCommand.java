package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class CheckEmailExistenceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String email = request.getParameter(RequestParameterName.EMAIL);

            UserService service = UserService.getInstance();

            if (service.checkEmailExistence(email)) {
                return AjaxResponseValue.POSITIVE;
            } else {
                return AjaxResponseValue.NEGATIVE;
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute Email checking command", e);
        }
    }
}

