package com.epam.finaltask.university.dao.impl;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.common.ProfileCommon;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;
import com.epam.finaltask.university.dao.util.bean.factory.impl.ProfileDaoBeanFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Sql profile dao.
 */
public class SqlProfileDaoImpl implements ProfileDao {

    private final ConnectionPool connectionPool;
    private int recordsCount;

    private SqlProfileDaoImpl() {
        connectionPool = ConnectionPool.getInstance();
    }

    public static class ProfileDaoHolder {
        public static final SqlProfileDaoImpl INSTANCE = new SqlProfileDaoImpl();
    }

    public static SqlProfileDaoImpl getInstance() {
        return ProfileDaoHolder.INSTANCE;
    }

    private static final String TERMS_ID_KEY = "terms_terms_id";
    private static final String FIND_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile " +
            "WHERE passport_id = ? AND status = 'ACTIVE' AND applied = FALSE";
    private static final String FIND_PROFILE_BY_LAST_NAME_QUERY = "SELECT SQL_CALC_FOUND_ROWS * FROM profile " +
            "WHERE last_name = ? AND status = 'ACTIVE' AND applied = FALSE LIMIT ?, ?";
    private static final String FIND_APPLIED_PROFILE_BY_PASSPORT_ID_QUERY = "SELECT * FROM profile " +
            "WHERE passport_id = ? AND status = 'ACTIVE' AND applied = TRUE";
    private static final String FIND_APPLIED_PROFILE_BY_LAST_NAME_QUERY = "SELECT SQL_CALC_FOUND_ROWS * FROM profile " +
            "WHERE last_name = ? AND status = 'ACTIVE' AND applied = TRUE LIMIT ?, ?";
    private static final String FIND_PROFILE_BY_ID_QUERY = "SELECT * FROM profile WHERE profile_id = ? " +
            "AND status = 'ACTIVE'";
    private static final String GET_ALL_PROFILES_QUERY = "SELECT * FROM profile WHERE status = 'ACTIVE' " +
            "AND applied = FALSE";
    private static final String GET_ALL_PROFILES_QUERY_COUNTED = "SELECT SQL_CALC_FOUND_ROWS * FROM profile " +
            "WHERE status = 'ACTIVE' AND applied = FALSE LIMIT ?, ?";
    private static final String GET_ALL_APPLIED_QUERY_COUNTED = "SELECT SQL_CALC_FOUND_ROWS * FROM profile " +
            "WHERE status = 'ACTIVE' AND applied = TRUE LIMIT ?, ?";
    private static final String CHECK_UPDATE_AVAILABILITY_QUERY = "SELECT profile_id FROM profile " +
            "WHERE user_user_id <> ? AND passport_id = ? AND status = 'ACTIVE'";
    private static final String CHECK_IF_APPLIED = "SELECT * from profile WHERE user_user_id = ? AND " +
            "status = 'ACTIVE' AND applied = TRUE";
    private static final String GET_COUNT_QUERY = "SELECT FOUND_ROWS()";
    private static final String GET_FOR_APPLY_QUERY = "SELECT profile.* FROM application INNER JOIN profile " +
            "ON profile.profile_id = application.profile_profile_id WHERE profile.free_form = ? AND " +
            "application.out_of_competition = ? AND profile.faculty_faculty_id = ? " +
            "AND profile.status = 'ACTIVE' AND application.status = 'ACTIVE' " +
            "ORDER BY profile.points DESC LIMIT ?";
    private static final String GET_WITH_SAME_POINT = "SELECT profile.* FROM application INNER JOIN profile " +
            "ON profile.profile_id = application.profile_profile_id WHERE profile.free_form = ? AND " +
            "application.out_of_competition = FALSE AND profile.points = ? AND profile.faculty_faculty_id = ? " +
            "AND profile.status = 'ACTIVE' AND application.status = 'ACTIVE' " +
            "ORDER BY application.date";
    private static final String GET_TERMS_ID_QUERY = "SELECT faculty.terms_terms_id FROM profile INNER JOIN faculty " +
            "ON profile.faculty_faculty_id = faculty.faculty_id WHERE profile.profile_id = ? " +
            "AND profile.status = 'ACTIVE' AND faculty.status = 'ACTIVE' ";
    private static final String GET_CONFIRMED_QUERY = "SELECT SQL_CALC_FOUND_ROWS profile.* FROM application INNER JOIN profile " +
            "ON profile.profile_id = application.profile_profile_id WHERE  profile.faculty_faculty_id = ? " +
            "AND profile.status = 'ACTIVE' AND application.confirmed = TRUE AND application.status = 'ACTIVE' " +
            "ORDER BY profile.points LIMIT ?, ?";

    /**
     * Add new profile
     *
     * @param profile
     * @return added profile,
     * @throws DaoException
     */
    @Override
    public Profile add(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            ProfileCommon service = ProfileCommon.getInstance();
            profile = service.createProfile(profile, connection);

            return profile;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Searching profile by passport ID
     *
     * @param passportId passport ID
     * @return found profile
     * @throws DaoException
     */
    @Override
    public Profile find(String passportId) throws DaoException {
        return findByPassportId(passportId, false);
    }

    /**
     * Searching profiles by last name with offset.
     *
     * @param lastName     last name
     * @param offset       searching offset
     * @param recordsCount records count
     * @return fis of profiles
     * @throws DaoException
     */
    @Override
    public List<Profile> findProfilesByLastName(String lastName, int offset, int recordsCount) throws DaoException {
        return findByLastName(lastName, offset, recordsCount, false);
    }

    /**
     * Search profile by profile ID
     *
     * @param profileId profile ID
     * @return found profile or null
     * @throws DaoException
     */
    @Override
    public Profile findById(long profileId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_PROFILE_BY_ID_QUERY);
        ) {
            statement.setLong(1, profileId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DaoBeanFactory<Profile> constructor = ProfileDaoBeanFactory.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Updates profile data
     *
     * @param profile profile
     * @return updated profile
     * @throws DaoException
     */
    @Override
    public Profile update(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
        ) {
            ProfileCommon profileCommon = ProfileCommon.getInstance();
            profile = profileCommon.updateProfile(profile, connection);

            return profile;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    @Override
    public Profile delete(String domain) throws DaoException {
        throw new UnsupportedOperationException("Profile can't be delete without account");
    }

    /**
     * Gets all profiles
     *
     * @return list of profiles
     * @throws DaoException
     */
    @Override
    public List<Profile> all() throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
        ) {
            List<Profile> profiles = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery(GET_ALL_PROFILES_QUERY);
            while (resultSet.next()) {
                DaoBeanFactory<Profile> constructor = ProfileDaoBeanFactory.getInstance();

                profiles.add(constructor.construct(resultSet));
            }

            return profiles;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }



    /**
     * Checks update availability
     *
     * @param profile profile
     * @return true if aupdate is available, else false
     * @throws DaoException
     */
    @Override
    public boolean checkUpdateAvailability(Profile profile) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHECK_UPDATE_AVAILABILITY_QUERY);
        ) {
            long userId = profile.getUserId();

            if (!checkApplied(connection, userId)) {
                statement.setLong(1, userId);
                statement.setString(2, profile.getPassportId());

                ResultSet resultSet = statement.executeQuery();
                return !resultSet.next();
            }
            return false;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    private boolean checkApplied(Connection connection, long userId) throws DaoException {
        try (
                PreparedStatement statement = connection.prepareStatement(CHECK_IF_APPLIED);
        ) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Searching applied application by passport ID
     *
     * @param passportId
     * @return found profile
     * @throws DaoException
     */
    @Override
    public Profile findApplied(String passportId) throws DaoException {
        return findByPassportId(passportId, true);
    }

    /**
     * Search applied applications by last name with offset
     *
     * @param lastName     last name
     * @param offset       offset
     * @param recordsCount tecords count
     * @return list of profiles
     * @throws DaoException
     */
    @Override
    public List<Profile> findAppliedByLastName(String lastName, int offset, int recordsCount) throws DaoException {
        return findByLastName(lastName, offset, recordsCount, true);
    }

    /**
     * Searching all applied profiles with offset
     *
     * @param offset       offset
     * @param recordsCount records count
     * @return
     * @throws DaoException
     */
    @Override
    public List<Profile> findAllApplied(int offset, int recordsCount) throws DaoException {
        return findAll(offset, recordsCount, true);
    }

    /**
     * Search all profiles with offset
     *
     * @param offset
     * @param recordsCount
     * @return
     * @throws DaoException
     */
    @Override
    public List<Profile> findAllProfiles(int offset, int recordsCount) throws DaoException {
        return findAll(offset, recordsCount, false);
    }

    @Override
    public List<Profile> findAllEnrolled(long facultyId, int offset, int recordsCount) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_CONFIRMED_QUERY);
        ) {
            List<Profile> profiles = new ArrayList<>();

            statement.setLong(1, facultyId);
            statement.setInt(2, offset);
            statement.setInt(3, recordsCount);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DaoBeanFactory<Profile> constructor = ProfileDaoBeanFactory.getInstance();

                profiles.add(constructor.construct(resultSet));
            }

            resultSet = statement.executeQuery(GET_COUNT_QUERY);
            if (resultSet.next()) {
                this.recordsCount = resultSet.getInt(1);
            }

            return profiles;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets records count
     *
     * @return int
     */
    @Override
    public int getRecordsCount() {
        return recordsCount;
    }

    @Override
    public long getProfileTermsId(long profileId) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_TERMS_ID_QUERY);
        ) {
            statement.setLong(1, profileId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(TERMS_ID_KEY);
            }

            return 0;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets all profiles with offset
     *
     * @param offset
     * @param recordsCount
     * @param isApplied
     * @return list of profiles
     * @throws DaoException
     */
    private List<Profile> findAll(int offset, int recordsCount, boolean isApplied) throws DaoException {
        String query;
        if (isApplied) {
            query = GET_ALL_APPLIED_QUERY_COUNTED;
        } else {
            query = GET_ALL_PROFILES_QUERY_COUNTED;
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            List<Profile> profiles = new ArrayList<>();

            statement.setInt(1, offset);
            statement.setInt(2, recordsCount);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DaoBeanFactory<Profile> constructor = ProfileDaoBeanFactory.getInstance();

                profiles.add(constructor.construct(resultSet));
            }

            resultSet = statement.executeQuery(GET_COUNT_QUERY);
            if (resultSet.next()) {
                this.recordsCount = resultSet.getInt(1);
            }

            return profiles;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Searching profile by passport ID
     *
     * @param passportId
     * @param isApplied
     * @return found profile or null
     * @throws DaoException
     */
    private Profile findByPassportId(String passportId, boolean isApplied) throws DaoException {
        String query;
        if (isApplied) {
            query = FIND_APPLIED_PROFILE_BY_PASSPORT_ID_QUERY;
        } else {
            query = FIND_PROFILE_BY_PASSPORT_ID_QUERY;
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, passportId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DaoBeanFactory<Profile> constructor = ProfileDaoBeanFactory.getInstance();

                return constructor.construct(resultSet);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Searching profiles by last name with offset and application status
     *
     * @param lastName
     * @param offset
     * @param number
     * @param isApplied
     * @return list of profiles
     * @throws DaoException
     */
    private List<Profile> findByLastName(String lastName, int offset,
                                         int number, boolean isApplied) throws DaoException {
        String query;
        if (isApplied) {
            query = FIND_APPLIED_PROFILE_BY_LAST_NAME_QUERY;
        } else {
            query = FIND_PROFILE_BY_LAST_NAME_QUERY;
        }

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
        ) {
            List<Profile> profiles = new ArrayList<>();
            statement.setString(1, lastName);
            statement.setInt(2, offset);
            statement.setInt(3, number);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DaoBeanFactory<Profile> constructor = ProfileDaoBeanFactory.getInstance();

                profiles.add(constructor.construct(resultSet));
            }

            resultSet = statement.executeQuery(GET_COUNT_QUERY);
            if (resultSet.next()) {
                this.recordsCount = resultSet.getInt(1);
            }

            return profiles;
        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets profiles to apply by faculty ID
     *
     * @param facultyId
     * @param isFreeForm
     * @param isOutOfCompetition
     * @param quota
     * @return list of profiles
     * @throws DaoException
     */
    @Override
    public List<Profile> getToApply(long facultyId, boolean isFreeForm, boolean isOutOfCompetition, int quota)
            throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_FOR_APPLY_QUERY);
        ) {
            statement.setBoolean(1, isFreeForm);
            statement.setBoolean(2, isOutOfCompetition);
            statement.setLong(3, facultyId);
            statement.setInt(4, quota);

            List<Profile> profiles = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DaoBeanFactory<Profile> factory = ProfileDaoBeanFactory.getInstance();

                profiles.add(factory.construct(resultSet));
            }

            return profiles;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }

    /**
     * Gets profiles with same points
     * @param facultyId
     * @param isFreeForm
     * @param points
     * @param quota
     * @return list of profiles
     * @throws DaoException
     */
    @Override
    public List<Profile> getWithSamePoints(long facultyId, boolean isFreeForm, int points) throws DaoException {
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_WITH_SAME_POINT);
        ) {
            statement.setBoolean(1, isFreeForm);
            statement.setInt(2, points);
            statement.setLong(3, facultyId);

            List<Profile> profiles = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DaoBeanFactory<Profile> factory = ProfileDaoBeanFactory.getInstance();

                profiles.add(factory.construct(resultSet));
            }

            return profiles;

        } catch (IllegalArgumentException | ConnectionPoolException | SQLException e) {
            throw new DaoException("Couldn't process operation", e);
        }
    }


}
