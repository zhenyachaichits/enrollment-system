package com.epam.finaltask.university.controller.command;


import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zheny Chaichits on 11/25/2015.
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
