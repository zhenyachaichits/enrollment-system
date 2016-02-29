package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.common.FacultyCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.FacultyDaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.ProfileDaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.UserDaoBeanFactory;

import java.sql.*;
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

    private static final String FIND_ALL_FACULTIES_QUERY = "SELECT * FROM faculty WHERE status = 'ACTIVE'";
    private static final String FIND_FACULTY_BY_NAME_QUERY = "SELECT * FROM faculty WHERE name = ? AND " +
            "status = 'ACTIVE'";


    @Override
    public Faculty add(Faculty faculty) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            FacultyCommon common = FacultyCommon.getInstance();
            faculty = common.createFaculty(faculty, connection);

            return faculty;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Faculty find(String name) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_FACULTY_BY_NAME_QUERY);
        ) {
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FacultyDaoBeanFactory factory = FacultyDaoBeanFactory.getInstance();

                return factory.construct(resultSet);
            } else {
                return null;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Faculty update(Faculty entity) throws DaoException {
        return null;
    }

    @Override
    public Faculty delete(String name) throws DaoException {
        return null;
    }

    @Override
    public List<Faculty> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Faculty> faculties = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_FACULTIES_QUERY);
            while (resultSet.next()) {
                DaoBeanFactory<Faculty> constructor = FacultyDaoBeanFactory.getInstance();

                faculties.add(constructor.construct(resultSet));
            }

            return faculties;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}
