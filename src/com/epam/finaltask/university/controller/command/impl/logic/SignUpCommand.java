package com.epam.finaltask.university.controller.command.impl.logic;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.controller.JspPageName;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.Command;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.command.exception.InvalidUserDataException;
import com.epam.finaltask.university.service.UserService;
import com.epam.finaltask.university.service.concurrent.LockingProfileService;
import com.epam.finaltask.university.service.concurrent.LockingUserService;
import com.epam.finaltask.university.service.exception.InvalidDataException;
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class SignUpCommand implements Command {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            User user = constructUser(request);
            Profile profile = constructProfile(request);

            LockingUserService userService = LockingUserService.getInstance();
            LockingProfileService profileService = LockingProfileService.getInstance();

            user = userService.createNewAccount(user);
            if (user != null) {
                profile.setUserId(user.getId());
                profile = profileService.createNewProfile(profile);
            }

            if (user == null || profile == null) {
                throw new InvalidUserDataException("Invalid user data. Couldn't sign up");
            }

            return JspPageName.INDEX;

        } catch (ServiceException | InvalidDataException e) {
            throw new CommandException("Couldn't execute authentication command", e);
        }
    }

    private User constructUser(HttpServletRequest request) {
        String email = request.getParameter(RequestParameterName.EMAIL);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    private Profile constructProfile(HttpServletRequest request) throws CommandException {
        try {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

            String passportId = request.getParameter(RequestParameterName.PASSPORT_ID);
            String firstName = request.getParameter(RequestParameterName.FIRST_NAME);
            String middleName = request.getParameter(RequestParameterName.MIDDLE_NAME);
            String lastName = request.getParameter(RequestParameterName.LAST_NAME);
            Calendar birthDate  = Calendar.getInstance();
            birthDate.setTime(formatter.parse(request.getParameter(RequestParameterName.BIRTH_DATE)));
            long facultyId = Long.parseLong(request.getParameter(RequestParameterName.FACULTY_ID));
            int points = Integer.parseInt(request.getParameter(RequestParameterName.TOTAL_POINTS));
            MedalType medal = MedalType.valueOf(request.getParameter(RequestParameterName.MEDAL_TYPE).toUpperCase());
            String privileges = request.getParameter(RequestParameterName.PRIVILEGES);
            String phone = request.getParameter(RequestParameterName.PHONE);
            String address = request.getParameter(RequestParameterName.ADDRESS);

            Profile profile = new Profile();

            profile.setPassportId(passportId);
            profile.setFirstName(firstName);
            profile.setMiddleName(middleName);
            profile.setLastName(lastName);
            profile.setBirthDate(birthDate);
            profile.setFacultyId(facultyId);
            profile.setPoints(points);
            profile.setMedalType(medal);
            if (!"".equals(privileges)) {
                profile.setPrivileges(privileges);
            }
            profile.setPhone(phone);
            profile.setAddress(address);

            return profile;
        } catch (ParseException | NumberFormatException e) {
            throw new CommandException("Couldn't construct profile bean", e);
        }
    }

}
