package com.epam.finaltask.university.test;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.FacultyDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlFacultyDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;


public class FacultyDaoTest {

    private static FacultyDao dao;
    private static Faculty testFaculty;

    private static final String TEST = "test";
    private static final int UPDATED = 10;
    private static final int POINTS = 0;
    private static final int QUOTA = 5;

    private static final String CREATE_TEST_TERMS_QUERY = "INSERT INTO terms (start_date, end_date) VALUES " +
            "('2016-03-05', '2016-03-05')";
    private static final String CREATE_TEST_FACULTY_QUERY = "INSERT INTO faculty (name, free_quota, paid_quota, terms_terms_id) " +
            "VALUES ('test', 5, 5,";
    private static String CREATE_TEST_FACULTY_SUBJECT_QUERY = "INSERT INTO faculty_has_subject (faculty_faculty_id, " +
            "subject_subject_id) VALUES (";
    private static final String CREATE_TEST_SUBJECT_QUERY = "INSERT INTO subject (name, min_points) VALUES " +
            "('test', 5)";

    private static final String DELETE_TEST_SUBJECT_QUERY = "DELETE FROM subject WHERE name = 'test'";
    private static final String DELETE_TEST_FACULTY_SUBJECT_QUERY = "DELETE FROM faculty_has_subject WHERE subject_subject_id = ";
    private static final String DELETE_TEST_FACULTY_QUERY = "DELETE FROM faculty WHERE name = 'test'";
    private static final String DELETE_TEST_TERMS_QUERY = "DELETE FROM terms WHERE start_date = '2016-03-05' " +
            "AND end_date = '2016-03-05'";


    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = SqlFacultyDaoImpl.getInstance();

        testFaculty = new Faculty();

        testFaculty.setName(TEST);
        testFaculty.setFreeQuota(QUOTA);
        testFaculty.setPaidQuota(QUOTA);

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
        long subjectId = TestHelper.executeDBAction(CREATE_TEST_SUBJECT_QUERY);
        TestHelper.executeDBAction(CREATE_TEST_FACULTY_SUBJECT_QUERY + facultyId + ", " + subjectId + ")");

        Set<Long> subjectSet = new HashSet<>();
        subjectSet.add(subjectId);
        testFaculty.setSubjects(subjectSet);
        testFaculty.setTermsId(termsId);
        testFaculty.setId(facultyId);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_FACULTY_SUBJECT_QUERY + testFaculty.getSubjects().iterator().next());
        TestHelper.executeDBAction(DELETE_TEST_FACULTY_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_SUBJECT_QUERY);
        TestHelper.executeDBAction(DELETE_TEST_TERMS_QUERY);

        testFaculty.setFreePoint(POINTS);
        testFaculty.setPaidPoint(POINTS);
    }

    @Test
    public void testAdd() throws DaoException {
        Faculty added = dao.add(testFaculty);

        assertNotNull(added);
        assertEquals(testFaculty, added);
    }

    @Test
    public void testFindByName() throws DaoException {
        Faculty found = dao.find(testFaculty.getName());

        assertNotNull(found);
        assertEquals(testFaculty, found);
    }

    @Test
    public void testFindById() throws DaoException {
        Faculty found = dao.find(testFaculty.getId());

        assertNotNull(found);
        assertEquals(testFaculty, found);
    }

    @Test
    public void testUpdate() throws DaoException {
        testFaculty.setFreeQuota(UPDATED);
        Faculty updated = dao.update(testFaculty);

        assertNotNull(updated);
        assertEquals(testFaculty, updated);
    }

    @Test
    public void testAll() throws DaoException {
        List<Faculty> faculties = dao.all();

        assertTrue(faculties.contains(testFaculty));
    }

    @Test
    public void testDeleteByName() throws DaoException {
        Faculty deleted = dao.delete(testFaculty.getName());

        assertNotNull(deleted);
    }

    @Test
    public void testDeleteById() throws DaoException {
        boolean result = dao.delete(testFaculty.getId());

        assertTrue(result);
    }

    @Test
    public void testCheckUpdateAvailability() throws DaoException {
        boolean result = dao.checkUpdateAvailability(testFaculty);

        assertTrue(result);
    }

    @Test
    public void testUpdatePoints() throws DaoException {
        testFaculty.setFreePoint(UPDATED);
        testFaculty.setPaidPoint(UPDATED);
        boolean result = dao.updatePoints(testFaculty.getId(), UPDATED, UPDATED);

        assertTrue(result);
    }
}
