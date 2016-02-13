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
import com.epam.finaltask.university.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zheny Chaichits on 11.02.2016.
 */
public class SignUpCommand implements Command {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static final String PASSPORT_ID = "passportID";
    public static final String FIRST_NAME = "firstName";
    public static final String MIDDLE_NAME = "middleName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTH_DATE = "dateBirth";
    public static final String FACULTY_ID = "facultyID";
    public static final String MEDAL_TYPE = "medal";
    public static final String PRIVILEGES = "privileges";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            User user = constructUser(request);
            Profile profile = constructProfile(request);

            UserService service = UserService.getInstance();
            user = service.authentificateUser(user);

            if (user != null) {
            } else {
                throw new InvalidUserDataException("Invalid user data. Couldn't sign in");
            }

            return JspPageName.INDEX;

        } catch (ServiceException e) {
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
            Date birthDate = formatter.parse(request.getParameter(RequestParameterName.BIRTH_DATE));
            long facultyId = Long.parseLong(request.getParameter(RequestParameterName.FACULTY_ID));
            MedalType medal = MedalType.valueOf(request.getParameter(RequestParameterName.MEDAL_TYPE));
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
            profile.setMedalType(medal);
            if (!"".equals(privileges)) {
                profile.setPrivileges(privileges);
            }
            profile.setPhone(phone);
            profile.setAddress(address);

            return profile;
        } catch (ParseException e) {
            throw new CommandException("Couldn't construct profile bean", e);
        }
    }

}
