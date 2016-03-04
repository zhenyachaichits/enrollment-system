package com.epam.finaltask.university.controller.command.impl.ajax;

import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.impl.ajax.response.AjaxResponseValue;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command Check account.
 */
public class CheckAccountCommand implements Command {
    /**
     * Execute checking user data validity befote authentication
     * @param request
     * @param response
     * @return constant from class AjaxResponseValue
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            String email = request.getParameter(RequestParameterName.EMAIL);
            String password = request.getParameter(RequestParameterName.PASSWORD);

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);

            UserService service = UserService.getInstance();

            if (service.checkAccountData(user)) {
                return AjaxResponseValue.POSITIVE;
            } else {
                return AjaxResponseValue.NEGATIVE;
            }

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute pre-authentication command", e);
        }
    }
}
