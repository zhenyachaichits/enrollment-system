package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.DateTypeConverter;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.TermsDaoBeanFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sql terms dao.
 */
public class SqlTermsDaoImpl implements TermsDao {

    private final ConnectionPool connectionPool;

    private SqlTermsDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class TermsDaoHolder {
        public static final SqlTermsDaoImpl INSTANCE = new SqlTermsDaoImpl();
    }

    public static SqlTermsDaoImpl getInstance() {
        return TermsDaoHolder.INSTANCE;
    }

    private static final String ADD_TERMS_QUERY = "INSERT INTO terms (start_date, end_date) VALUES (?, ?)";
    private static final String GET_ALL_TERMS_QUERY = "SELECT * FROM terms WHERE status = 'ACTIVE'";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM terms WHERE terms_id = ? AND " +
            "status = 'ACTIVE'";
    private static final String UPDATE_TERMS_QUERY = "UPDATE terms SET start_date = ?, end_date = ? " +
            "WHERE terms_id = ? AND status = 'ACTIVE'";
    private static final String DELETE_QUERY = "UPDATE terms SET status = 'DELETED' WHERE terms_id = ?";
    private static final String CHECK_EXISTENCE_QUERY = "SELECT * FROM terms WHERE start_date = ? AND " +
            "end_date = ? AND status = 'ACTIVE'";
    private static final String CHECK_UPDATE_AVAILABILITY_QUERY = "SELECT * FROM terms WHERE terms_id <> ? AND " +
            "start_date = ? AND end_date = ? AND status = 'ACTIVE'";

    /**
     * Creates new terms
     *
     * @param terms terms
     * @return terms or null if not created
     * @throws DaoException
     */
    @Override
    public Terms add(Terms terms) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADD_TERMS_QUERY);
        ) {
            statement.setDate(1, DateTypeConverter.convertToSqlDate(terms.getStartDate()));
            statement.setDate(2, DateTypeConverter.convertToSqlDate(terms.getEndDate()));
            ;

            int result = statement.executeUpdate();

            if (result != 0) {
                return terms;
            } else {
                return null;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Search terms by ID
     *
     * @param id terms ID
     * @return terms or null if couldn't find
     * @throws DaoException
     */
    @Override
    public Terms find(Long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);
        ) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                DaoBeanFactory<Terms> factory = TermsDaoBeanFactory.getInstance();

                return factory.construct(resultSet);
            } else {
                return null;
            }

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Update terms data
     *
     * @param terms
     * @return updated terms or null if couldn't update
     * @throws DaoException
     */
    @Override
    public Terms update(Terms terms) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_TERMS_QUERY);
        ) {
            statement.setDate(1, DateTypeConverter.convertToSqlDate(terms.getStartDate()));
            statement.setDate(2, DateTypeConverter.convertToSqlDate(terms.getEndDate()));
            statement.setLong(3, terms.getId());

            int result = statement.executeUpdate();

            return result != 0 ? terms : null;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Delete terms by ID
     *
     * @param id terms ID
     * @return deleted terms or null
     * @throws DaoException
     */
    @Override
    public Terms delete(Long id) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
        ) {
            statement.setLong(1, id);

            int result = statement.executeUpdate();

            if (result != 0) {
                Terms terms = new Terms();
                terms.setId(id);

                return terms;
            } else {
                return null;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets all terms
     *
     * @return list of terms
     * @throws DaoException
     */
    @Override
    public List<Terms> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Terms> userList = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(GET_ALL_TERMS_QUERY);
            while (resultSet.next()) {
                DaoBeanFactory<Terms> factory = TermsDaoBeanFactory.getInstance();

                userList.add(factory.construct(resultSet));
            }

            return userList;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Checks terms existence
     *
     * @param terms
     * @return true if terms already exists, else false
     * @throws DaoException
     */
    @Override
    public boolean checkExistence(Terms terms) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_EXISTENCE_QUERY);
        ) {
            statement.setDate(1, DateTypeConverter.convertToSqlDate(terms.getStartDate()));
            statement.setDate(2, DateTypeConverter.convertToSqlDate(terms.getEndDate()));

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Checks terms update availability
     *
     * @param terms
     * @return true if update is avaliable, else false
     * @throws DaoException
     */
    @Override
    public boolean checkUpdateAvailability(Terms terms) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY_QUERY);
        ) {
            statement.setLong(1, terms.getId());
            statement.setDate(2, DateTypeConverter.convertToSqlDate(terms.getStartDate()));
            statement.setDate(3, DateTypeConverter.convertToSqlDate(terms.getEndDate()));

            ResultSet resultSet = statement.executeQuery();

            return !resultSet.next();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

}
