package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Check passport id existence command.
 */
public class CheckPassportIdExistenceCommand implements Command {
    /**
     * Execute passport ID checking to restrict creating profile with existing passport  data
     * @param request
     * @param response
     * @return constant from class AjaxResponseValue
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String passportId = request.getParameter(RequestParameterName.PASSPORT_ID);

            ProfileService service = ProfileService.getInstance();

            if (service.checkPassportIdExistence(passportId)) {
                return AjaxResponseValue.POSITIVE.toString();
            } else {
                return AjaxResponseValue.NEGATIVE.toString();
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute Email checking command", e);
        }
    }
}
