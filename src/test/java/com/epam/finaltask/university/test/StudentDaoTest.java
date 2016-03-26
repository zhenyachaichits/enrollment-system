package com.epam.finaltask.university.test;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.bean.type.MedalType;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlStudentDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;


public class StudentDaoTest {

    private static StudentDao dao;
    private static Student testStudent;

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

    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = SqlStudentDaoImpl.getInstance();

        User user = new User();
        user.setEmail(TEST);
        user.setPassword(TEST);
        user.setRole(UserType.STUDENT);

        Profile profile = new Profile();
        profile.setPassportId(TEST);
        profile.setFirstName(TEST);
        profile.setMiddleName(TEST);
        profile.setLastName(TEST);
        Calendar birthDate = new GregorianCalendar(YEAR, MONTH, DAY);
        profile.setBirthDate(birthDate);
        profile.setPhone(TEST);
        profile.setAddress(TEST);
        profile.setPoints(POINTS);
        profile.setMedalType(MedalType.NONE);
        profile.setFreeForm(true);

        testStudent = new Student(user, profile);

    }

    @AfterClass
    public static void destroy() throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
    }

    @Before
    public void createRecord() throws ConnectionPoolException, SQLException {
        long termsId = TestHelper.executeDBAction(CREATE_TEST_TERMS_QUERY);
        long facultyId = TestHelper.executeDBAction(CREATE_TEST_FACULTY_QUERY + termsId + ")");
        long userId = TestHelper.executeDBAction(CREATE_TEST_USER_QUERY);
        testStudent.getUser().setId(userId);
        testStudent.getProfile().setUserId(userId);
        testStudent.getProfile().setFacultyId(facultyId);
        long profileId = TestHelper.executeDBAction(CREATE_TEST_PROFILE_QUERY + facultyId + "," + userId + ")");
        testStudent.getProfile().setId(profileId);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_PROFILE_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_USER_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_FACULTY_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_TERMS_QUERY);

    }

    @Test
    public void testAdd() throws DaoException {
        Student added = dao.add(testStudent);

        assertNotNull(added);
        assertEquals(testStudent, added);
    }

    @Test
    public void testFind() throws DaoException {
        Student found = dao.find(testStudent.getUser().getEmail());

        assertNotNull(found);
        assertEquals(testStudent, found);
    }

    @Test
    public void testUpdate() throws DaoException {
        testStudent.getProfile().setFirstName(UPDATE);
        Student updated = dao.update(testStudent);

        assertNotNull(updated);
        assertEquals(testStudent, updated);
    }

    @Test
    public void testCheckStudentExistence() throws DaoException {
        boolean result = dao.checkStudentExistence(testStudent);

        assertTrue(result);
    }

    @Test
    public void testCheckUpdateAvailability() throws DaoException {
        boolean result = dao.checkUpdateAvailability(testStudent);

        assertTrue(result);
    }

}
