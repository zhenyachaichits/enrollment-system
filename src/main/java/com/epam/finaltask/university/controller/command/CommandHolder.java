package com.epam.finaltask.university.controller.command;


import com.epam.finaltask.university.controller.command.impl.ajax.*;
import com.epam.finaltask.university.controller.command.impl.error.NoSuchCommand;
import com.epam.finaltask.university.controller.command.impl.logic.update.ConfirmFacultyApplicationsCommand;
import com.epam.finaltask.university.controller.command.impl.logic.LogOutCommand;
import com.epam.finaltask.university.controller.command.impl.logic.SetLocaleCommand;
import com.epam.finaltask.university.controller.command.impl.logic.creation.*;
import com.epam.finaltask.university.controller.command.impl.logic.deletion.*;
import com.epam.finaltask.university.controller.command.impl.logic.search.*;
import com.epam.finaltask.university.controller.command.impl.logic.update.*;
import com.epam.finaltask.university.controller.command.impl.navigation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * The type Command helper.
 */
public class CommandHolder {

    private static final Logger LOG = LogManager.getLogger(CommandHolder.class.getClass());

    private static final String EMPTY_COMMAND_NAME = "";

    private static Map<CommandName, Command> commandMap = new HashMap<>();

    private final static CommandHolder instance = new CommandHolder();

    private CommandHolder() {
        commandMap.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());

        commandMap.put(CommandName.CHECK_EMAIL, new CheckEmailExistenceCommand());
        commandMap.put(CommandName.CHECK_PASSPORT_ID, new CheckPassportIdExistenceCommand());
        commandMap.put(CommandName.CHECK_FACULTY_NAME, new CheckFacultyNameExistenceCommand());
        commandMap.put(CommandName.CHECK_SUBJECT_NAME, new CheckSubjectNameExistenceCommand());

        commandMap.put(CommandName.AUTHENTICATE, new AuthenticateUserCommand());
        commandMap.put(CommandName.COOKIE_AUTHENTICATE, new AuthenticateWithCookieCommand());
        commandMap.put(CommandName.LOG_OUT, new LogOutCommand());
        commandMap.put(CommandName.SIGN_UP, new SignUpCommand());

        commandMap.put(CommandName.GO_HOME, new GoHomeCommand());
        commandMap.put(CommandName.GO_BACK, new GoBackCommand());
        commandMap.put(CommandName.GO_SIGNUP, new GoSignUpCommand());
        commandMap.put(CommandName.GO_STATISTICS, new GoStatisticsCommand());
        commandMap.put(CommandName.GO_PROFILE, new GoProfileCommand());
        commandMap.put(CommandName.GO_SUPPORT_SEARCH, new GoSupportSearchCommand());
        commandMap.put(CommandName.GO_APPLY_FORM, new GoApplyFormCommand());
        commandMap.put(CommandName.GO_APPLICATION, new GoApplicationCommand());
        commandMap.put(CommandName.GO_APPLICATION_DATA, new GoApplicationDataCommand());
        commandMap.put(CommandName.GO_USER_MANAGEMENT, new GoUserManagementCommand());
        commandMap.put(CommandName.GO_USER_DATA, new GoUserDataCommand());
        commandMap.put(CommandName.GO_FACULTY_MANAGEMENT, new GoFacultyManagementCommand());
        commandMap.put(CommandName.GO_SUBJECT_MANAGEMENT, new GoSubjectManagementCommand());
        commandMap.put(CommandName.GO_TERMS_MANAGEMENT, new GoTermsManagementCommand());
        commandMap.put(CommandName.GO_FACULTY_DATA, new GoFacultyDataCommand());

        commandMap.put(CommandName.SEARCH_PROFILE_BY_PASSPORT, new FindProfileByPassportIdCommand());
        commandMap.put(CommandName.SEARCH_PROFILES_BY_LAST_NAME, new FindProfilesByLastNameCommand());
        commandMap.put(CommandName.SEARCH_APPLIED_BY_PASSPORT, new FindAppliedByPassportIdCommand());
        commandMap.put(CommandName.SEARCH_APPLIED_BY_LAST_NAME, new FindAppliedByLastNameCommand());
        commandMap.put(CommandName.SEARCH_ALL_PROFILES, new FindAllProfilesCommand());
        commandMap.put(CommandName.SEARCH_ALL_APPLIED, new FindAllAppliedCommand());
        commandMap.put(CommandName.SEARCH_USERS_BY_ROLE, new FindUsersByRoleCommand());
        commandMap.put(CommandName.SEARCH_ENROLLED_PROFILES, new FindEnrolledCommand());

        commandMap.put(CommandName.UPDATE_PROFILE, new UpdateProfileCommand());
        commandMap.put(CommandName.UPDATE_STUDENT_DATA, new UpdateStudentDataCommand());
        commandMap.put(CommandName.UPDATE_USER, new UpdateUserDataCommand());
        commandMap.put(CommandName.UPDATE_FACULTY_DATA, new UpdateFacultyDataCommand());
        commandMap.put(CommandName.UPDATE_SUBJECT, new UpdateSubjectDataCommand());
        commandMap.put(CommandName.UPDATE_TERMS, new UpdateTermsCommand());
        commandMap.put(CommandName.UPDATE_FACULTY_POINTS, new UpdateFacultyPointsCommand());

        commandMap.put(CommandName.CONFIRM_FACULTY_APPLICATIONS, new ConfirmFacultyApplicationsCommand());
        commandMap.put(CommandName.RESET_FACULTY_APPLICATIONS, new ResetFacultyApplicationsCommand());

        commandMap.put(CommandName.DELETE_APPLICATION, new DeleteApplicationCommand());
        commandMap.put(CommandName.DELETE_PROFILE, new DeleteProfileCommand());
        commandMap.put(CommandName.DELETE_USER, new DeleteUserCommand());
        commandMap.put(CommandName.DELETE_FACULTY, new DeleteFacultyCommand());
        commandMap.put(CommandName.DELETE_SUBJECT, new DeleteSubjectCommand());
        commandMap.put(CommandName.DELETE_TERMS, new DeleteTermsCommand());

        commandMap.put(CommandName.CREATE_APPLICATION, new CreateApplicationCommand());
        commandMap.put(CommandName.CREATE_USER, new CreateUserCommand());
        commandMap.put(CommandName.CREATE_FACULTY, new CreateFacultyCommand());
        commandMap.put(CommandName.CREATE_SUBJECT, new CreateSubjectCommand());
        commandMap.put(CommandName.CREATE_TERMS, new CreateTermsCommand());

        commandMap.put(CommandName.GET_SUBJECTS, new GetFacultySubjectsCommand());

        commandMap.put(CommandName.SET_LOCALE, new SetLocaleCommand());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CommandHolder getInstance() {
        return instance;
    }


    /**
     * Gets command.
     *
     * @param commandName the command name
     * @return the command
     */
    public Command getCommand(String commandName) {
        try {
            commandName = prepareCommandName(commandName);

            CommandName name = CommandName.valueOf(commandName);
            return commandMap.get(name);

        } catch (IllegalArgumentException e) {
            LOG.error("Couldn't find command with such name. Default command was returned", e);
            return commandMap.get(CommandName.NO_SUCH_COMMAND);
        }
    }

    /**
     * Prepares and formats command name string.
     *
     * @param commandName
     * @return prepared string
     */
    private String prepareCommandName(String commandName) {
        if (commandName == null) {
            commandName = EMPTY_COMMAND_NAME;
        }

        return commandName.replace('-', '_').toUpperCase();
    }

}

