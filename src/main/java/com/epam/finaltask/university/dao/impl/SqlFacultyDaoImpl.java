package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.FacultyDaoBeanFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Sql faculty dao.
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

    private static final String FIND_ALL_FACULTIES_QUERY = "SELECT faculty.*, faculty_has_subject.* FROM faculty  " +
            "INNER JOIN faculty_has_subject ON faculty.faculty_id = faculty_has_subject.faculty_faculty_id " +
            "WHERE faculty.status = 'ACTIVE' AND faculty_has_subject.status = 'ACTIVE'";
    private static final String FIND_FACULTY_BY_NAME_QUERY = "SELECT faculty.*, faculty_has_subject.* FROM faculty  " +
            "INNER JOIN faculty_has_subject ON faculty.faculty_id = faculty_has_subject.faculty_faculty_id " +
            "WHERE faculty.name = ? AND faculty.status = 'ACTIVE' AND faculty_has_subject.status = 'ACTIVE'";
    private static final String FIND_FACULTY_BY_ID_QUERY = "SELECT faculty.*, faculty_has_subject.* FROM faculty  " +
            "INNER JOIN faculty_has_subject ON faculty.faculty_id = faculty_has_subject.faculty_faculty_id " +
            "WHERE faculty.faculty_id = ? AND faculty.status = 'ACTIVE' AND faculty_has_subject.status = 'ACTIVE'";
    private static final String ADD_FACULTY_QUERY = "INSERT INTO faculty (name, free_quota, paid_quota, terms_terms_id) " +
            "VALUES (?, ?, ?, ?)";
    private static String ADD_FACULTY_SUBJECT_QUERY = "INSERT INTO faculty_has_subject (faculty_faculty_id, " +
            "subject_subject_id) VALUES (?, ?)";
    private static final String CHECK_UPDATE_AVAILABILITY = "SELECT * FROM faculty WHERE faculty_id <> ? " +
            "AND name = ?";
    private static final String UPDATE_FACULTY_QUERY = "UPDATE faculty SET name = ?, free_quota = ?, paid_quota = ?, " +
            "terms_terms_id = ? WHERE faculty_id = ? AND status = 'ACTIVE'";
    private static final String CHECK_DELETE_AVAILABILITY = "SELECT faculty.* FROM faculty INNER JOIN profile " +
            "ON faculty.faculty_id = profile.faculty_faculty_id WHERE faculty.faculty_id = ?";
    private static final String DELETE_FACULTY_BY_ID_QUERY = "UPDATE faculty SET status = 'DELETED' " +
            "WHERE faculty_id = ?";
    private static final String DELETE_FACULTY_BY_NAME_QUERY = "UPDATE faculty SET status = 'DELETED' WHERE name = ?";
    private static final String UPDATE_POINTS_QUERY = "UPDATE faculty SET free_point = ?, paid_point = ? " +
            "WHERE faculty_id = ? AND status = 'ACTIVE'";

    /**
     * Creates new faculty.
     *
     * @param faculty
     * @return added faculty in case of success, else false
     * @throws DaoException
     */
    @Override
    public Faculty add(Faculty faculty) throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(ADD_FACULTY_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getFreeQuota());
            statement.setInt(3, faculty.getPaidQuota());
            statement.setLong(4, faculty.getTermsId());

            int facultyResult = statement.executeUpdate();

            int subjectsResult = 0;
            if (facultyResult != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    faculty.setId(generatedKeys.getLong(1));
                }
                statement = connection.prepareStatement(ADD_FACULTY_SUBJECT_QUERY);

                for (Long subjectId : faculty.getSubjects()) {
                    statement.setLong(1, faculty.getId());
                    statement.setLong(2, subjectId);

                    subjectsResult = statement.executeUpdate();
                    if (subjectId == 0) {
                        break;
                    }
                }
            }

            if (facultyResult != 0 && subjectsResult != 0) {
                connection.commit();
                return faculty;
            } else {
                connection.rollback();
                return null;
            }

        } catch (ConnectionPoolException | SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                // TODO: 16.02.2016 logger?
            }
            throw new DaoException("Couldn't process operation", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    // TODO: 19.02.2016 logger
                }
                connectionPool.closeConnection(connection);
            }
        }
    }

    /**
     * Search faculty by name
     *
     * @param name faculty name
     * @return found faculty or null if faculty was not found
     * @throws DaoException
     */
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

    /**
     * Update faculty data.
     *
     * @param faculty
     * @return updated faculty or null if couldn't update
     * @throws DaoException
     */
    @Override
    public Faculty update(Faculty faculty) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_FACULTY_QUERY);
        ) {
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getFreeQuota());
            statement.setInt(3, faculty.getPaidQuota());
            statement.setLong(4, faculty.getTermsId());
            statement.setLong(5, faculty.getId());

            int result = statement.executeUpdate();

            return result != 0 ? faculty : null;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Deleting faculty by name
     *
     * @param name faculty name
     * @return deleted faculty or null if couldn't delete
     * @throws DaoException
     */
    @Override
    public Faculty delete(String name) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_FACULTY_BY_NAME_QUERY);
        ) {
            statement.setString(1, name);

            int result = statement.executeUpdate();

            if (result != 0) {
                Faculty faculty = new Faculty();
                faculty.setName(name);

                return faculty;
            } else {
                return null;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets all faculties
     *
     * @return list of faculties
     * @throws DaoException
     */
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

    /**
     * Searching faculties by faculty ID
     *
     * @param facultyId faculty ID
     * @return found faculty or null if couldn't find
     * @throws DaoException
     */
    @Override
    public Faculty find(long facultyId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_FACULTY_BY_ID_QUERY);
        ) {
            statement.setLong(1, facultyId);

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

    /**
     * Checks faculte update availability
     *
     * @param faculty faculty
     * @return true if update is available, else false
     * @throws DaoException
     */
    @Override
    public boolean checkUpdateAvailability(Faculty faculty) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY);
        ) {
            statement.setLong(1, faculty.getId());
            statement.setString(2, faculty.getName());

            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Delete faculty by faculty ID
     *
     * @param facultyId faculty ID
     * @return true if faculty is deleted, else false
     * @throws DaoException
     */
    @Override
    public boolean delete(long facultyId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_FACULTY_BY_ID_QUERY);
        ) {
            statement.setLong(1, facultyId);

            int result = statement.executeUpdate();

            return result != 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Updates faculty points by faculty ID
     * @param facultyId faculty ID
     * @param freePoint free form points
     * @param paidPoint paid form points
     * @return true if updated, else false
     * @throws DaoException
     */
    @Override
    public boolean updatePoints(long facultyId, int freePoint, int paidPoint) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_POINTS_QUERY);
        ) {
            statement.setInt(1, freePoint);
            statement.setInt(2, paidPoint);
            statement.setLong(3, facultyId);

            int result = statement.executeUpdate();

            return result != 0;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public boolean checkDeletionAvailability(long facultyId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_DELETE_AVAILABILITY);
        ) {
            statement.setLong(1, facultyId);

            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }
}
