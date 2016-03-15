package com.epam.finaltask.university.controller.util;

import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.exception.AccessDeniedException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.command.exception.InvalidSessionException;

import javax.servlet.http.HttpSession;


/**
 * Access manager.
 */
public class AccessManager {

    /**
     * Manage access to command by checking session existence and access level.
     *
     * @param session     the session
     * @param accessLevel the access level
     * @throws AccessDeniedException   the access denied exception
     * @throws InvalidSessionException the invalid session exception
     */
    public static void provideAccess(HttpSession session, UserType accessLevel)
            throws AccessDeniedException, InvalidSessionException {

        checkSession(session);
        UserType currentType = (UserType) session.getAttribute(SessionParameterName.ROLE);
        if (!accessLevel.equals(currentType)) {
            throw new AccessDeniedException("Access to resource denied. User role doesn't match needed.");
        }
    }

    public static void denyAccess(HttpSession session, UserType accessLevel)
            throws AccessDeniedException, InvalidSessionException {

        checkSession(session);
        UserType currentType = (UserType) session.getAttribute(SessionParameterName.ROLE);
        if (accessLevel.equals(currentType)) {
            throw new AccessDeniedException("Access to resource denied. User role doesn't match needed.");
        }
    }

    private static void checkSession(HttpSession session) throws InvalidSessionException {
        if (session == null) {
            throw new InvalidSessionException("User session doesn't exist");
        }
    }
}
