package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class SqlFacultyDaoImpl implements FacultyDao {

    private final ConnectionPool connectionPool;

    private SqlFacultyDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class FacultyDaoHolder {
        public static final SqlFacultyDaoImpl INSTANCE = new SqlFacultyDaoImpl();
    }

    public static SqlFacultyDaoImpl getInstance() {
        return FacultyDaoHolder.INSTANCE;
    }

    private static final String ID_KEY = "faculty_id";
    private static final String NAME_KEY = "name";
    private static final String FREE_QUOTA_KEY = "free_quota";
    private static final String PAID_QUOTA_KEY = "paid_quota";
    private static final String FREE_POINT_KEY = "free_point";
    private static final String PAID_POINT_KEY = "paid_point";
    private static final String TERMS_KEY = "terms_terms_id";

    private static final String FIND_ALL_FACULTIES = "SELECT * FROM faculty";

    @Override
    public Faculty add(Faculty entity) throws DaoException {
        return null;
    }

    @Override
    public Faculty find(Faculty domain) throws DaoException {
        return null;
    }

    @Override
    public Faculty update(Faculty entity) throws DaoException {
        return null;
    }

    @Override
    public Faculty delete(Faculty domain) throws DaoException {
        return null;
    }

    @Override
    public List<Faculty> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Faculty> faculties = new ArrayList<>();

            Faculty faculty = new Faculty();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_FACULTIES);
            while (resultSet.next()) {
                faculty.setId(resultSet.getLong(ID_KEY));
                faculty.setName(resultSet.getString(NAME_KEY));
                faculty.setFreeQuota(resultSet.getInt(FREE_QUOTA_KEY));
                faculty.setPaidQuota(resultSet.getInt(PAID_QUOTA_KEY));
                faculty.setFreePoint(resultSet.getInt(FREE_POINT_KEY));
                faculty.setPaidPoint(resultSet.getInt(PAID_POINT_KEY));
                faculty.setTermsId(resultSet.getLong(TERMS_KEY));

                faculties.add(faculty);
            }

            return faculties;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}
