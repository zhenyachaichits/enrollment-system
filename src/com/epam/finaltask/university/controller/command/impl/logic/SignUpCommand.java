package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.SessionParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.CommandName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidDataException;
import com.epam.finaltask.university.controller.util.BeanConstructor;
import com.epam.finaltask.university.service.exception.InvalidServiceDataException;
import com.epam.finaltask.university.service.impl.concurrent.LockingStudentService;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class SignUpCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            User user = BeanConstructor.constructUser(request);
            Profile profile = BeanConstructor.constructProfile(request);
            Student student = new Student(user, profile);

            LockingStudentService service = LockingStudentService.getInstance();

            student = service.createNewAccount(student);
            if (student != null) {
                HttpSession session = request.getSession(true);

                session.setAttribute(SessionParameterName.EMAIL, student.getUser().getEmail());
                session.setAttribute(SessionParameterName.ROLE, student.getUser().getRole());

            } else {
                throw new InvalidDataException("Invalid user data. Couldn't sign up");
            }

            return CommandName.GO_HOME.getQueryString();

        } catch (ServiceException e) {
            throw new CommandException("Couldn't execute authentication command", e);
        }
    }
}
