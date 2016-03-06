package com.epam.finaltask.university.test;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.ProfileDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlProfileDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;


public class ProfileDaoTest {

    private static ProfileDao dao;
    private static Profile testProfile;

    private static final String TEST = "test";
    private static final String UPDATE = "update";
    private static final int YEAR = 2007;
    private static final int MONTH = 1;
    private static final int DAY = 28;
    private static final int POINTS = 228;

    private static final String CREATE_TEST_TERMS_QUERY = "INSERT INTO terms (start_date, end_date) VALUES " +
            "('2016-03-05', '2016-03-05')";
    private static final String CREATE_TEST_FACULTY_QUERY = "INSERT INTO faculty (name, free_quota, paid_quota, terms_terms_id) " +
            "VALUES ('test', 5, 5,";
    private static final String CREATE_TEST_USER_QUERY = "INSERT INTO user (email, password_hash) values ('test', 'test')";
    private static final String CREATE_TEST_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, " +
            "birth_date, phone, address, points, medal, free_form, faculty_faculty_id, user_user_id) " +
            "VALUES ('test', 'test', 'test', 'test', '2007-02-28', 'test', 'test', 228, 'NONE', true, ";

    private static final String DELETE_TEST_PROFILE_QUERY = "DELETE FROM profile WHERE passport_id = 'test'";
    private static final String DELETE_TEST_USER_QUERY = "DELETE FROM user WHERE email = 'test'";
    private static final String DELETE_TEST_FACULTY_QUERY = "DELETE FROM faculty WHERE name = 'test'";
    private static final String DELETE_TEST_TERMS_QUERY = "DELETE FROM terms WHERE start_date = '2016-03-05' " +
            "AND end_date = '2016-03-05'";

    private static final String UPDATE_TEST_PROFILE_APPLIED_QUERY = "UPDATE profile SET applied = TRUE WHERE " +
            "passport_id = 'test'";

    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = SqlProfileDaoImpl.getInstance();

        testProfile = new Profile();
        testProfile.setPassportId(TEST);
        testProfile.setFirstName(TEST);
        testProfile.setMiddleName(TEST);
        testProfile.setLastName(TEST);
        Calendar birthDate = new GregorianCalendar(YEAR, MONTH, DAY);
        testProfile.setBirthDate(birthDate);
        testProfile.setPhone(TEST);
        testProfile.setAddress(TEST);
        testProfile.setPoints(POINTS);
        testProfile.setMedalType(MedalType.NONE);
        testProfile.setFreeForm(true);
    }

    @AfterClass
    public static void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
    }

    @Before
    public void createRecord() throws ConnectionPoolException, SQLException {
        long termsId = TestHelper.executeDBAction(CREATE_TEST_TERMS_QUERY);
        long facultyId = TestHelper.executeDBAction(CREATE_TEST_FACULTY_QUERY + termsId + ")");
        long userId = TestHelper.executeDBAction(CREATE_TEST_USER_QUERY);
        testProfile.setUserId(userId);
        testProfile.setFacultyId(facultyId);
        long profileId = TestHelper.executeDBAction(CREATE_TEST_PROFILE_QUERY + facultyId + "," + userId + ")");
        testProfile.setId(profileId);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_PROFILE_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_USER_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_FACULTY_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_TERMS_QUERY);

        testProfile.setApplied(false);
    }

    @Test
    public void testAdd() throws DaoException {
        Profile added = dao.add(testProfile);

        assertNotNull(added);
        assertEquals(testProfile, added);
    }

    @Test
    public void testFind() throws DaoException {
        Profile found = dao.find(testProfile.getPassportId());

        assertNotNull(found);
        assertEquals(testProfile, found);
    }

    @Test
    public void testUpdate() throws DaoException {
        testProfile.setFirstName(UPDATE);
        Profile updated = dao.update(testProfile);

        assertNotNull(updated);
        assertEquals(testProfile, updated);
    }

    @Test
    public void testAll() throws DaoException {
        List<Profile> profiles = dao.all();

        assertTrue(profiles.contains(testProfile));
    }

    @Test
    public void testFindProfilesByLastName() throws DaoException {
        int possibleNumber = (int) testProfile.getId();
        List<Profile> profiles = dao.findProfilesByLastName(testProfile.getLastName(), 0, possibleNumber);

        assertTrue(profiles.contains(testProfile));
    }

    @Test
    public void testFindById() throws DaoException {
        Profile found = dao.findById(testProfile.getId());

        assertNotNull(found);
        assertEquals(testProfile, found);
    }

    @Test
    public void testCheckUpdateAvailability() throws DaoException {
        boolean result = dao.checkUpdateAvailability(testProfile);

        assertTrue(result);
    }

    @Test
    public void testFindAppliedById() throws DaoException, ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(UPDATE_TEST_PROFILE_APPLIED_QUERY);
        testProfile.setApplied(true);
        Profile found = dao.findApplied(testProfile.getPassportId());

        assertNotNull(found);
        assertEquals(testProfile, found);
    }

    @Test
    public void testFindAppliedByLastName() throws DaoException, ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(UPDATE_TEST_PROFILE_APPLIED_QUERY);
        testProfile.setApplied(true);
        int possibleNumber = (int) testProfile.getId();
        List<Profile> profiles = dao.findAppliedByLastName(testProfile.getLastName(), 0, possibleNumber);

        assertTrue(profiles.contains(testProfile));
    }

    @Test
    public void testFindAllProfiles() throws DaoException {
        int possibleNumber = (int) testProfile.getId();
        List<Profile> profiles = dao.findAllProfiles(0, possibleNumber);

        assertTrue(profiles.contains(testProfile));
    }

    @Test
    public void testFindAllApplied() throws DaoException, ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(UPDATE_TEST_PROFILE_APPLIED_QUERY);
        testProfile.setApplied(true);
        int possibleNumber = (int) testProfile.getId();
        List<Profile> profiles = dao.findAllApplied(0, possibleNumber);

        assertTrue(profiles.contains(testProfile));
    }
}
