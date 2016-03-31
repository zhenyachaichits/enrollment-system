package com.epam.finaltask.university.controller.command.impl.logic.update;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.bean.factory.CommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.exception.CommandBeanFactoryException;
import com.epam.finaltask.university.controller.util.bean.factory.impl.ProfileCommandBeanFactory;
import com.epam.finaltask.university.controller.util.bean.factory.impl.UserCommandBeanFactory;
import com.epam.finaltask.university.service.ProfileService;
import com.epam.finaltask.university.service.concurrent.LockingUserService;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.concurrent.LockingStudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Update profile command.
 */
public class UpdateProfileCommand implements Command {
    /**
     * Execute current user profile update.
     * Access is allowed for users with status: STUDENT
     * @param request
     * @param response
     * @return current page or home pare redirection query
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);
            AccessManager.provideAccess(session, UserType.STUDENT);
            long userId = (Long) session.getAttribute(SessionParameterName.UID);

            CommandBeanFactory<User> userCompiler = UserCommandBeanFactory.getInstance();
            CommandBeanFactory<Profile> profileCompiler = ProfileCommandBeanFactory.getInstance();

            User user = userCompiler.constructBean(request);
            user.setId(userId);
            Profile profile = profileCompiler.constructBean(request);
            Student student = new Student(user, profile);

            ProfileService profileService = ProfileService.getInstance();
            if (profileService.checkUpdateAvailability(profile)) {
                LockingStudentService service = LockingStudentService.getInstance();
                student = service.updateStudentProfile(student);
                if (student == null) {
                    throw new InvalidDataException("Couldn't find student from session");
                }
            } else {
                LockingUserService userService = LockingUserService.getInstance();
                user = userService.updateUser(user);
                if (user == null) {
                    throw new InvalidDataException("Couldn't update user data");
                }
            }

            request.setAttribute(RequestParameterName.STUDENT, student);
            String currentQuery = (String) session.getAttribute(SessionParameterName.CURRENT_PAGE);
            return currentQuery == null ? CommandName.GO_HOME.getQueryString() : currentQuery;
            
        } catch (CommandBeanFactoryException | NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
