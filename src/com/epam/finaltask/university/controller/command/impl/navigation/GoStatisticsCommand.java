package com.epam.finaltask.university.controller.command.impl.navigation;

import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class GoStatisticsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return JspPageName.STATICTICS_PAGE;
    }
}
