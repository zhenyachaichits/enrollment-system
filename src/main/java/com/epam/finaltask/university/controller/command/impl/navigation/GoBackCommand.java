package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GoBackCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(true);
        String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);

        return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;
    }
}
