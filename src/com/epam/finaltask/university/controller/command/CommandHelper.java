package com.epam.finaltask.university.controller.command;


import com.epam.finaltask.university.controller.command.impl.ajax.*;
import com.epam.finaltask.university.controller.command.impl.error.NoSuchCommand;
import com.epam.finaltask.university.controller.command.impl.logic.*;
import com.epam.finaltask.university.controller.command.impl.logic.delete.DeleteApplicationCommand;
import com.epam.finaltask.university.controller.command.impl.logic.delete.DeleteProfileCommand;
import com.epam.finaltask.university.controller.command.impl.logic.search.*;
import com.epam.finaltask.university.controller.command.impl.logic.update.UpdateProfileCommand;
import com.epam.finaltask.university.controller.command.impl.logic.update.UpdateStudentDataCommand;
import com.epam.finaltask.university.controller.command.impl.navigation.*;

import java.util.HashMap;
import java.util.Map;


public class CommandHelper {

    private static final String EMPTY_COMMAND_NAME = "";

    private static Map<CommandName, Command> commandMap = new HashMap<>();

    private final static CommandHelper instance = new CommandHelper();

    private CommandHelper() {
        commandMap.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());

        commandMap.put(CommandName.CHECK_ACCOUNT, new CheckAccountCommand());
        commandMap.put(CommandName.AUTHENTICATE, new AuthenticateCommand());
        commandMap.put(CommandName.COOKIE_AUTHENTICATE, new AuthenticateWithCookieCommand());
        commandMap.put(CommandName.LOG_OUT, new LogOutCommand());
        commandMap.put(CommandName.CHECK_EMAIL, new CheckEmailExistenceCommand());
        commandMap.put(CommandName.CHECK_PASSPORT_ID, new CheckPassportIdExistenceCommand());
        commandMap.put(CommandName.SIGN_UP, new SignUpCommand());

        commandMap.put(CommandName.GO_HOME, new GoHomeCommand());
        commandMap.put(CommandName.GO_SIGNUP, new GoSignUpCommand());
        commandMap.put(CommandName.GO_STATISTICS, new GoStatisticsCommand());
        commandMap.put(CommandName.GO_PROFILE, new GoProfileCommand());
        commandMap.put(CommandName.GO_SUPPORT_SEARCH, new GoSupportSearchCommand());
        commandMap.put(CommandName.GO_APPLY_FORM, new GoApplyFormCommand());
        commandMap.put(CommandName.GO_APPLICATION, new GoApplicationCommand());
        commandMap.put(CommandName.GO_APPLICATION_DATA, new GoApplicationDataCommand());
        commandMap.put(CommandName.GO_USER_MANAGEMENT, new GoUserManagementCommand());

        commandMap.put(CommandName.SEARCH_PROFILE_BY_PASSPORT, new FindProfileByPassportIdCommand());
        commandMap.put(CommandName.SEARCH_PROFILES_BY_LAST_NAME, new FindProfilesByLastNameCommand());
        commandMap.put(CommandName.SEARCH_APPLIED_BY_PASSPORT, new FindAppliedByPassportIdCommand());
        commandMap.put(CommandName.SEARCH_APPLIED_BY_LAST_NAME, new FindAppliedByLastNameCommand());
        commandMap.put(CommandName.SEARCH_ALL_PROFILES, new FindAllProfilesCommand());
        commandMap.put(CommandName.SEARCH_ALL_APPLIED, new FindAllAppliedCommand());
        commandMap.put(CommandName.SEARCH_USERS_BY_ROLE, new FindUsersByRoleCommand());

        commandMap.put(CommandName.UPDATE_PROFILE, new UpdateProfileCommand());
        commandMap.put(CommandName.UPDATE_STUDENT_DATA, new UpdateStudentDataCommand());

        commandMap.put(CommandName.DELETE_APPLICATION, new DeleteApplicationCommand());
        commandMap.put(CommandName.DELETE_PROFILE, new DeleteProfileCommand());

        commandMap.put(CommandName.CREATE_APPLICATION, new CreateApplicationCommand());

        commandMap.put(CommandName.GET_SUBJECTS, new GetFacultySubjectsCommand());

        commandMap.put(CommandName.SET_LOCALE, new SetLocaleCommand());
    }

    public static CommandHelper getInstance() {
        return instance;
    }


    public Command getCommand(String commandName) {
        try {
            commandName = prepareCommandName(commandName);

            CommandName name = CommandName.valueOf(commandName);
            return commandMap.get(name);

        } catch (IllegalArgumentException e) {
            //todo: add logger
            return commandMap.get(CommandName.NO_SUCH_COMMAND);
        }
    }

    private String prepareCommandName(String commandName) {
        if (commandName == null) {
            commandName = EMPTY_COMMAND_NAME;
        }

        return commandName.replace('-', '_').toUpperCase();
    }

}

