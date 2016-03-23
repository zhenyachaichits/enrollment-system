package com.epam.finaltask.university.controller.util;

import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.command.exception.AccessDeniedException;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.command.exception.InvalidSessionException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Controller exception handler.
 */
public class ExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ExceptionHandler.class.getClass());

    private static final String ERROR_MESSAGE = "ERROR";

    /**
     * Handle access exception string.
     *
     * @param e the exception
     * @return the access error page name
     */
    public static String handleAccessException(AccessDeniedException e) {
        if (LOG.isEnabled(Level.INFO)) {
            LOG.info("User access level is not enough", e);
        }
        return JspPageName.ACCESS_ERROR_PAGE;
    }

    /**
     * Handle session exception string.
     *
     * @param e the exception
     * @return the session error page name
     */
    public static String handleSessionException(InvalidSessionException e) {
        if (LOG.isEnabled(Level.INFO)) {
            LOG.info("User session invalidated", e);
        }
        return JspPageName.SESSION_ERROR_PAGE;
    }

    /**
     * Handle data exception string.
     *
     * @param e the exception
     * @return the data error page name
     */
    public static String handleDataException(InvalidDataException e) {
        if (LOG.isEnabled(Level.INFO)) {
            LOG.info("Operation canceled. User data is invalid", e);
        }
        return JspPageName.DATA_ERROR_PAGE;
    }

    /**
     * Handle command exception string.
     *
     * @param e      the exception
     * @param isAjax the is request ajax
     * @return the command error page name
     */
    public static String handleCommandException(CommandException e, boolean isAjax) {
        LOG.error("Couldn't execute command", e);
        if (isAjax) {
            return ERROR_MESSAGE;
        } else {
            return JspPageName.ERROR_PAGE;
        }
    }


}
