package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.FacultyDaoBeanFactory;

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

    private static final String FIND_ALL_FACULTIES = "SELECT * FROM faculty WHERE status = 'ACTIVE'";

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

            ResultSet resultSet = statement.executeQuery(FIND_ALL_FACULTIES);
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
