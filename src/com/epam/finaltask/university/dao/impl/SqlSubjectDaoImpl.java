package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.SubjectDaoBeanFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public class SqlSubjectDaoImpl implements SubjectDao {

    private final ConnectionPool connectionPool;

    private SqlSubjectDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class SubjectDaoHolder {
        public static final SqlSubjectDaoImpl INSTANCE = new SqlSubjectDaoImpl();
    }

    public static SqlSubjectDaoImpl getInstance() {
        return SubjectDaoHolder.INSTANCE;
    }

    private static final String GET_SUBJECTS_BY_FACULTY_ID = "SELECT subject.* FROM faculty_has_subject " +
            "INNER JOIN subject ON subject.subject_id = faculty_has_subject.subject_subject_id " +
            "WHERE faculty_has_subject.faculty_faculty_id = ? AND subject.status = 'ACTIVE'";

    private static final String GET_ALL_SUBJECTS = "SELECT * FROM subject WHERE status = 'ACTIVE'";
    private static final String GET_SUBJECT_BY_ID_QUERY = "SELECT * FROM subject WHERE subject_id = ? AND status = 'ACTIVE'";
    private static final String GET_SUBJECT_BY_NAME_QUERY = "SELECT * FROM subject WHERE name = ? AND status = 'ACTIVE'";

    @Override
    public List<Subject> getSubjectsByFacultyId(long facultyId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_SUBJECTS_BY_FACULTY_ID);
        ) {
            List<Subject> subjects = new ArrayList<>();

            statement.setLong(1, facultyId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DaoBeanFactory<Subject> factory = SubjectDaoBeanFactory.getInstance();

                subjects.add(factory.construct(resultSet));
            }

            return subjects;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Subject getSubjectById(long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_ID_QUERY);
        ) {
            Subject subject = new Subject();
            subject.setId(id);

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                DaoBeanFactory<Subject> factory = SubjectDaoBeanFactory.getInstance();

                return factory.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Subject add(Subject entity) throws DaoException {
        return null;
    }

    @Override
    public Subject find(String name) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_NAME_QUERY);
        ) {
            Subject subject = new Subject();
            subject.setName(name);

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                DaoBeanFactory<Subject> factory = SubjectDaoBeanFactory.getInstance();

                return factory.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Subject update(Subject entity) throws DaoException {
        return null;
    }

    @Override
    public Subject delete(String domain) throws DaoException {
        return null;
    }

    @Override
    public List<Subject> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Subject> userList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(GET_ALL_SUBJECTS);
            while (resultSet.next()) {
                DaoBeanFactory<Subject> factory = SubjectDaoBeanFactory.getInstance();

                userList.add(factory.construct(resultSet));
            }

            return userList;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

}
