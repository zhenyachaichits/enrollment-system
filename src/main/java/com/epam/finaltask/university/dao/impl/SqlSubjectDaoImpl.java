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
 * Sql subject dao.
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
    private static final String ADD_SUBJECT_QUERY = "INSERT INTO subject (name, min_points) VALUES (?, ?)";
    private static final String UPDATE_SUBJECT_QUERY = "UPDATE subject SET name = ?, min_points = ? WHERE subject_id = ? " +
            "AND status = 'ACTIVE'";
    private static final String DELETE_SUBJECT_BY_NAME_QUERY = "UPDATE subject SET status = 'DELETED' " +
            "WHERE name = ? ";
    private static final String DELETE_SUBJECT_BY_ID_QUERY = "UPDATE subject SET status = 'DELETED' " +
            "WHERE subject_id = ? ";
    private static final String CHECK_UPDATE_AVAILABILITY_QUERY = "SELECT * FROM subject WHERE subject_id <> ? AND " +
            "name = ? AND status = 'ACTIVE'";

    /**
     * Gets all subjects by faculty ID
     *
     * @param facultyId
     * @return list of subjects
     * @throws DaoException
     */
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

    /**
     * Gets subject by ID
     *
     * @param id subject ID
     * @return subject or null, if couldn't find
     * @throws DaoException
     */
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

    /**
     * Delete subject by ID
     *
     * @param id subject ID
     * @return true if deleted, else false
     * @throws DaoException
     */
    @Override
    public boolean delete(long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_BY_ID_QUERY);
        ) {
            statement.setLong(1, id);

            int result = statement.executeUpdate();

            return result != 0;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Checks subject's update availability
     *
     * @param subject
     * @return true if update available, else false
     * @throws DaoException
     */
    @Override
    public boolean checkUpdateAvailability(Subject subject) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY_QUERY);
        ) {
            statement.setLong(1, subject.getId());
            statement.setString(2, subject.getName());

            ResultSet resultSet = statement.executeQuery();

            return !resultSet.next();
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Creates new subject
     *
     * @param subject
     * @return created subject or null if wasn't created
     * @throws DaoException
     */
    @Override
    public Subject add(Subject subject) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_SUBJECT_QUERY);
        ) {
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getMinPoint());

            int result = statement.executeUpdate();

            return result == 0 ? null : subject;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Search subject by name
     *
     * @param name subject name
     * @return found subject or null
     * @throws DaoException
     */
    @Override
    public Subject find(String name) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_NAME_QUERY);
        ) {
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

    /**
     * Updates subject data
     *
     * @param subject
     * @return updated subject or null if couldn't update
     * @throws DaoException
     */
    @Override
    public Subject update(Subject subject) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT_QUERY);
        ) {
            statement.setString(1, subject.getName());
            statement.setInt(2, subject.getMinPoint());
            statement.setLong(3, subject.getId());

            int result = statement.executeUpdate();

            return result == 0 ? null : subject;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Delete subject by name
     *
     * @param name
     * @return deleted subject or null if was not deleted
     * @throws DaoException
     */
    @Override
    public Subject delete(String name) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT_BY_NAME_QUERY);
        ) {
            Subject subject = new Subject();
            subject.setName(name);

            statement.setString(1, name);

            int result = statement.executeUpdate();

            return result == 0 ? null : subject;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets all subjects
     *
     * @return list of subjects
     * @throws DaoException
     */
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
