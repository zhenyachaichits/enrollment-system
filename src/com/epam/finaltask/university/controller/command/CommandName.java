package com.epam.finaltask.university.controller.command;

/**
 * Created by Zheny Chaichits on 22.01.16.
 */
public enum CommandName {
    GO_HOME, GO_SIGNUP, GO_STATISTICS, GO_PROFILE, GO_SUPPORT_SEARCH, GO_APPLY_FORM, GO_APPLICATION,
    CHECK_ACCOUNT, AUTHENTICATE, COOKIE_AUTHENTICATE, LOG_OUT, SIGN_UP, CHECK_EMAIL, CHECK_PASSPORT_ID,
    UPDATE_PROFILE, UPDATE_STUDENT_DATA,
    GET_SUBJECTS,
    SET_LOCALE,

    SEARCH_PROFILE_BY_PASSPORT, SEARCH_PROFILES_BY_LAST_NAME, SEARCH_APPLIED_BY_PASSPORT, SEARCH_APPLIED_BY_LAST_NAME,
    CREATE_APPLICATION,

    NO_SUCH_COMMAND;

    private static String COMMAND_QUERY_NAME = "?command=";

    public String getQueryString() {
        return COMMAND_QUERY_NAME + this.toString().toLowerCase();
    }
}
