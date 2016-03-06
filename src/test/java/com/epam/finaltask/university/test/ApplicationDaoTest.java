package com.epam.finaltask.university.test;

import com.epam.finaltask.university.bean.Application;
import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.dao.ApplicationDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlApplicationDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;


public class ApplicationDaoTest {

    private static ApplicationDao dao;
    private static Application testApplication;

    private static final int YEAR = 2016;
    private static final int MONTH = 2;
    private static final int DAY = 5;


    private static final String CREATE_TEST_APPLICATION_QUERY = "INSERT INTO application (out_of_competition, date, " +
            "profile_profile_id, profile_faculty_faculty_id) VALUES (false, '2016-03-05', ";
    private static final String CREATE_TEST_TERMS_QUERY = "INSERT INTO terms (start_date, end_date) VALUES " +
            "('2016-03-05', '2016-03-05')";
    private static final String CREATE_TEST_FACULTY_QUERY = "INSERT INTO faculty (name, free_quota, paid_quota, terms_terms_id) " +
            "VALUES ('test', 5, 5,";
    private static final String CREATE_TEST_USER_QUERY = "INSERT INTO user (email, password_hash) values ('test', 'test')";
    private static final String CREATE_TEST_PROFILE_QUERY = "INSERT INTO profile (passport_id, first_name, middle_name, last_name, " +
            "birth_date, phone, address, points, medal, free_form, faculty_faculty_id, user_user_id) " +
            "VALUES ('test', 'test', 'test', 'test', '2007-02-28', 'test', 'test', 228, 'NONE', true, ";


    private static final String DELETE_TEST_APPLICATION_QUERY = "DELETE FROM application WHERE profile_faculty_faculty_id = ";
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

        dao = SqlApplicationDaoImpl.getInstance();

        testApplication = new Application();
        testApplication.setOutOfCompetition(false);
        testApplication.setDate(new GregorianCalendar(YEAR, MONTH, DAY));
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
        testApplication.setFacultyId(facultyId);
        long profileId = TestHelper.executeDBAction(CREATE_TEST_PROFILE_QUERY + facultyId + "," + userId + ")");
        testApplication.setProfileId(profileId);
        long applicationId = TestHelper.executeDBAction(CREATE_TEST_APPLICATION_QUERY + profileId + ", " + facultyId + ")");
        testApplication.setId(applicationId);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_APPLICATION_QUERY + testApplication.getFacultyId());
        TestHelper.executeDBAction(DELETE_TEST_PROFILE_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_USER_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_FACULTY_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_TERMS_QUERY);
    }

    @Test
    public void testAdd() throws DaoException {
        Application added = dao.add(testApplication);

        assertNotNull(added);
        assertEquals(testApplication, added);
    }

    @Test
    public void testFind() throws DaoException {
        Application found = dao.find(testApplication.getProfileId());

        assertNotNull(found);
        assertEquals(testApplication, found);
    }

    @Test
    public void testAll() throws DaoException {
        List<Application> profiles = dao.all();

        assertTrue(profiles.contains(testApplication));
    }

    @Test
    public void testDelete() throws DaoException {
        Application deleted = dao.delete(testApplication.getProfileId());
    }

    @Test
    public void testConfirmApplication() throws DaoException {
        boolean result = dao.confirmApplication(testApplication.getProfileId());

        assertTrue(result);
    }


}
