package com.epam.finaltask.university.controller.command.impl.logic.creation;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.bean.type.UserType;
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
 * Create terms command.
 */
public class CreateTermsCommand implements Command {
    /**
     * Execute creation of new terms.
     * Access is allowed for users with status: ADMIN
     * @param request
     * @param response
     * @return terms management page name redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.ADMIN);

            CommandBeanFactory<Terms> factory = TermsCommandBeanFactory.getInstance();
            Terms terms = factory.constructBean(request);

            LockingTermsService service = LockingTermsService.getInstance();
            terms = service.createTerms(terms);

            if (terms == null) {
                throw new InvalidDataException("Invalid terms data.");
            }

            return CommandName.GO_TERMS_MANAGEMENT.getQueryString();

        } catch (CommandBeanFactoryException | ServiceException e) {
            throw new CommandException("Couldn't execute terms creation command", e);
        }
    }
}
