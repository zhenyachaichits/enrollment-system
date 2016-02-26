package com.epam.finaltask.university.controller.util.compiler.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.controller.RequestParameterName;
import com.epam.finaltask.university.controller.command.exception.CommandException;
import com.epam.finaltask.university.controller.util.compiler.BeanCompiler;
import com.epam.finaltask.university.controller.util.compiler.exception.BeanCompilerException;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public class ProfileCompiler implements BeanCompiler<Profile> {

    private ProfileCompiler() {
    }

    public static class ProfileCompilerHolder {
        public static final ProfileCompiler INSTANCE = new ProfileCompiler();
    }

    public static ProfileCompiler getInstance() {
        return ProfileCompilerHolder.INSTANCE;
    }

    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String CHECKED = "on";

    @Override
    public Profile compile(HttpServletRequest request) throws BeanCompilerException {
        try {
            DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

            String passportId = request.getParameter(RequestParameterName.PASSPORT_ID).trim();
            String firstName = request.getParameter(RequestParameterName.FIRST_NAME).trim();
            String middleName = request.getParameter(RequestParameterName.MIDDLE_NAME).trim();
            String lastName = request.getParameter(RequestParameterName.LAST_NAME).trim();
            Calendar birthDate  = Calendar.getInstance();
            birthDate.setTime(formatter.parse(request.getParameter(RequestParameterName.BIRTH_DATE)));
            long facultyId = Long.parseLong(request.getParameter(RequestParameterName.FACULTY_ID));
            boolean isFree = CHECKED.equals(request.getParameter(RequestParameterName.FREE_FORM));
            int points = Integer.parseInt(request.getParameter(RequestParameterName.TOTAL_POINTS));
            MedalType medal = MedalType.valueOf(request.getParameter(RequestParameterName.MEDAL_TYPE).toUpperCase());
            String privileges = request.getParameter(RequestParameterName.PRIVILEGES).trim();
            String phone = request.getParameter(RequestParameterName.PHONE).trim();
            String address = request.getParameter(RequestParameterName.ADDRESS).trim();

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
            throw new BeanCompilerException("Couldn't compile profile bean", e);
        }
    }
}
