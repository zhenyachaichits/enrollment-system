package com.epam.finaltask.university.controller.util;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 20.02.2016.
 */
public class BeanConstructor {

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String CHECKED = "on";

    public static User constructUser(HttpServletRequest request) {
        String email = request.getParameter(RequestParameterName.EMAIL);
        String password = request.getParameter(RequestParameterName.PASSWORD);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }

    public static Profile constructProfile(HttpServletRequest request) throws CommandException {
        try {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

            String passportId = request.getParameter(RequestParameterName.PASSPORT_ID);
            String firstName = request.getParameter(RequestParameterName.FIRST_NAME);
            String middleName = request.getParameter(RequestParameterName.MIDDLE_NAME);
            String lastName = request.getParameter(RequestParameterName.LAST_NAME);
            Calendar birthDate  = Calendar.getInstance();
            birthDate.setTime(formatter.parse(request.getParameter(RequestParameterName.BIRTH_DATE)));
            long facultyId = Long.parseLong(request.getParameter(RequestParameterName.FACULTY_ID));
            boolean isFree = CHECKED.equals(request.getParameter(RequestParameterName.FREE_FORM));
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
            profile.setFreeForm(isFree);
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