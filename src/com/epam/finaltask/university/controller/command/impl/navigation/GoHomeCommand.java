package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.UrlCompiler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 06.02.16.
 */
public class GoHomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionParameterName.CURRENT_PAGE, UrlCompiler.compile(request));

        return JspPageName.INDEX;
    }
}
