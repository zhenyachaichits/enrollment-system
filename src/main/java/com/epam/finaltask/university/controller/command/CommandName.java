package com.epam.finaltask.university.controller.command;


/**
 * The enum Command name.
 */
public enum CommandName {
    GO_HOME,
    GO_BACK,
    GO_SIGNUP,
    GO_STATISTICS,
    GO_PROFILE,
    GO_SUPPORT_SEARCH,
    GO_APPLY_FORM,
    GO_APPLICATION,
    GO_APPLICATION_DATA,
    GO_USER_MANAGEMENT,
    GO_USER_DATA,
    GO_FACULTY_MANAGEMENT,
    GO_SUBJECT_MANAGEMENT,
    GO_TERMS_MANAGEMENT,
    GO_FACULTY_DATA,

    CHECK_EMAIL,
    CHECK_PASSPORT_ID,
    CHECK_FACULTY_NAME,
    CHECK_SUBJECT_NAME,

    AUTHENTICATE,
    COOKIE_AUTHENTICATE,
    LOG_OUT,
    SIGN_UP,

    CONFIRM_FACULTY_APPLICATIONS,
    RESET_FACULTY_APPLICATIONS,
    UPDATE_PROFILE,
    UPDATE_STUDENT_DATA,
    UPDATE_USER,
    UPDATE_FACULTY_DATA,
    UPDATE_SUBJECT,
    UPDATE_TERMS,
    UPDATE_FACULTY_POINTS,
    CREATE_APPLICATION,
    CREATE_USER,
    CREATE_FACULTY,
    CREATE_SUBJECT,
    CREATE_TERMS,
    DELETE_APPLICATION,
    DELETE_PROFILE,
    DELETE_USER,
    DELETE_FACULTY,
    DELETE_SUBJECT,
    DELETE_TERMS,

    GET_SUBJECTS,
    SET_LOCALE,

    SEARCH_PROFILE_BY_PASSPORT,
    SEARCH_PROFILES_BY_LAST_NAME,
    SEARCH_APPLIED_BY_PASSPORT,
    SEARCH_APPLIED_BY_LAST_NAME,
    SEARCH_ALL_PROFILES,
    SEARCH_ALL_APPLIED,
    SEARCH_USERS_BY_ROLE,
    SEARCH_ENROLLED_PROFILES,

    NO_SUCH_COMMAND;

    private static String CONTROLLER_NAME = "action";
    private static String COMMAND_QUERY_NAME = "?command=";

    /**
     * Prepares query string for command name.
     *
     * @return the query string
     */
    public String getQueryString() {
        return CONTROLLER_NAME + COMMAND_QUERY_NAME + this.toString().toLowerCase();
    }
}

