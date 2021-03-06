package com.epam.finaltask.university.dao.common;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.util.DateTypeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Faculty common  methods which can be used for transactions ect..
 */
public class FacultyCommon {
    private FacultyCommon() {
    }

    public static class FacultyCommonHolder {
        public static final FacultyCommon INSTANCE = new FacultyCommon();
    }

    public static FacultyCommon getInstance() {
        return FacultyCommonHolder.INSTANCE;
    }

    private static final String ADD_FACULTY_QUERY = "INSERT INTO faculty (name, free_quota, paid_quota, terms_terms_id) " +
            "VALUES (?, ?, ?, ?)";

    /**
     * Create new faculty.
     *
     * @param faculty    the faculty
     * @param connection the connection
     * @return created faculty
     * @throws SQLException the sql exception
     */
    public Faculty createFaculty(Faculty faculty, Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(ADD_FACULTY_QUERY);
        ) {

            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getFreeQuota());
            statement.setLong(3, faculty.getPaidQuota());
            statement.setLong(4, faculty.getTermsId());

            int result = statement.executeUpdate();

            if (result != 0) {
                return faculty;
            } else {
                return null;
            }
        }
    }
}
