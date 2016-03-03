package com.epam.finaltask.university.controller.command;


import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute command returning string for controller.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     * @throws CommandException the command exception
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
