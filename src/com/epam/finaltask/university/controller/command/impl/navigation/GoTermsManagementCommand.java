package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.UrlBuilder;
import com.epam.finaltask.university.service.TermsService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Zheny Chaichits on 02.03.2016.
 */
public class GoTermsManagementCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.manageAccess(session, UserType.ADMIN);

            TermsService service = TermsService.getInstance();
            List<Terms> termsList = service.getAllTerms();

            request.setAttribute(RequestParameterName.TERMS_LIST, termsList);
            session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlBuilder.build(request));

            return JspPageName.TERMS_MANAGEMENT_PAGE;
        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute terms navigation command");
        }
    }
}
