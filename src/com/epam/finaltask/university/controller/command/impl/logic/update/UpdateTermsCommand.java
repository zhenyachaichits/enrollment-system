package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;
import com.epam.finaltask.university.controller.util.bean.factory.impl.TermsCommandBeanFactory;
import com.epam.finaltask.university.service.concurrent.LockingTermsService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Update terms command.
 */
public class UpdateTermsCommand implements Command {
    /**
     * Execute update of terms by terms ID.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return terms management page redirect query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.manageAccess(session, UserType.ADMIN);

            CommandBeanFactory<Terms> factory = TermsCommandBeanFactory.getInstance();
            Terms terms = factory.constructBean(request);

            String idStr = request.getParameter(RequestParameterName.TERMS_ID);
            long id = Long.parseLong(idStr);
            terms.setId(id);

            LockingTermsService service = LockingTermsService.getInstance();

            terms = service.updateTerms(terms);

            if (terms == null) {
                throw new InvalidDataException("Couldn't update profile");
            }

            return CommandName.GO_TERMS_MANAGEMENT.getQueryString();

        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
