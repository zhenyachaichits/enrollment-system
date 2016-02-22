package com.epam.finaltask.university.controller.util;

import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.command.exception.AccessDeniedException;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.command.exception.InvalidSessionException;

/**
 * Created by Zheny Chaichits on 22.02.2016.
 */
public class ExceptionHandler {

    private static final String ERROR_MESSAGE = "ERROR";

    public static String handleAccessException(AccessDeniedException e) {
        // TODO: 22.02.2016 logging
        return JspPageName.ACCESS_ERROR_PAGE;
    }

    public static String handleSessionException(InvalidSessionException e) {
        // TODO: 22.02.2016 logging
        return JspPageName.SESSION_ERROR_PAGE;
    }

    public static String handleDataException(InvalidDataException e) {
        // TODO: 22.02.2016 logging
        return JspPageName.DATA_ERROR_PAGE;
    }

    public static String handleCommandException(CommandException e, boolean isAjax) {
        //LOG.error("Couldn't execute command", e);
        if (isAjax) {
            return ERROR_MESSAGE;
        } else {
            return JspPageName.ERROR_PAGE;
        }
    }


}
