package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.AccessManager;
import com.epam.finaltask.university.controller.util.BeanConstructor;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.impl.concurrent.LockingStudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 20.02.2016.
 */
public class UpdateProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            HttpSession session = request.getSession(false);

            AccessManager.manageAccess(session, UserType.STUDENT);

            long user_id = (Long) session.getAttribute(SessionParameterName.UID);

            LockingStudentService service = LockingStudentService.getInstance();

            User user = BeanConstructor.constructUser(request);
            user.setId(user_id);
            Profile profile = BeanConstructor.constructProfile(request);
            Student student = new Student(user, profile);

            student = service.updateStudentProfile(student);

            if (student != null) {
                request.setAttribute(RequestParameterName.STUDENT, student);
            } else {
                throw new InvalidDataException("Couldn't find student from session");
            }

            return JspPageName.PROFILE_PAGE;
        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException("Couldn't process profile update command");
        }
    }
}
