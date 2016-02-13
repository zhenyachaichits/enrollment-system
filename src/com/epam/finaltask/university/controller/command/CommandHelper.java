package com.epam.finaltask.university.controller.command;


import com.epam.finaltask.university.controller.command.impl.ajax.AuthenticateWithCookieCommand;
import com.epam.finaltask.university.controller.command.impl.ajax.CheckAccountCommand;
import com.epam.finaltask.university.controller.command.impl.ajax.CheckEmailExistenceCommand;
import com.epam.finaltask.university.controller.command.impl.error.NoSuchCommand;
import com.epam.finaltask.university.controller.command.impl.logic.AuthenticateCommand;
import com.epam.finaltask.university.controller.command.impl.logic.LogOutCommand;
import com.epam.finaltask.university.controller.command.impl.logic.SetLocaleCommand;
import com.epam.finaltask.university.controller.command.impl.logic.SignUpCommand;
import com.epam.finaltask.university.controller.command.impl.navigation.GoHomeCommand;
import com.epam.finaltask.university.controller.command.impl.navigation.GoSignUpCommand;
import com.epam.finaltask.university.controller.command.impl.navigation.GoStatisticsCommand;

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
        commandMap.put(CommandName.SIGN_UP, new SignUpCommand());

        commandMap.put(CommandName.GO_HOME, new GoHomeCommand());
        commandMap.put(CommandName.GO_SIGNUP, new GoSignUpCommand());
        commandMap.put(CommandName.GO_STATISTICS, new GoStatisticsCommand());

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

