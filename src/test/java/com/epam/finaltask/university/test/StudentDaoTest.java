package com.epam.finaltask.university.test;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlSubjectDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zheny Chaichits on 05.03.2016.
 */
public class StudentDaoTest {


    private static SubjectDao dao;
    private static Subject testSubject;

    private static final String NAME = "test";
    private static final int MIN_POINTS = 0;
    private static final int UPDATED_MIN_POINTS = 5;

    private static final String CREATE_TEST_SUBJECT_QUERY = "INSERT INTO subject (name, min_points) VALUES " +
            "('" + NAME + "', " + MIN_POINTS + ")";
    private static final String DELETE_TEST_SUBJECT_QUERY = "DELETE FROM subject WHERE name = '" + NAME + "'";

    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = SqlSubjectDaoImpl.getInstance();

        testSubject = new Subject();
        testSubject.setName(NAME);
        testSubject.setMinPoint(MIN_POINTS);
    }

    @AfterClass
    public static void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
    }

    @Before
    public void createRecord() throws ConnectionPoolException, SQLException {
        long id = TestHelper.executeDBAction(CREATE_TEST_SUBJECT_QUERY);
        testSubject.setId(id);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_SUBJECT_QUERY);
        testSubject.setMinPoint(MIN_POINTS);
    }

    @Test
    public void testAdd() throws DaoException {
        Subject subject = dao.add(testSubject);

        assertNotNull(subject);
    }

    @Test
    public void testFind() throws DaoException {
        Subject found = dao.find(testSubject.getName());

        assertNotNull(found);
        assertEquals(testSubject, found);
    }

    @Test
    public void testUpdate() throws DaoException {
        testSubject.setMinPoint(UPDATED_MIN_POINTS);
        Subject updated = dao.update(testSubject);

        assertNotNull(updated);
        assertEquals(testSubject, updated);
    }

    @Test
    public void testAll() throws DaoException {
        List<Subject> subjects = dao.all();

        assertTrue(subjects.contains(testSubject));
    }

    @Test
    public void testDelete() throws DaoException {
        Subject deleted = dao.delete(testSubject.getName());

        assertNotNull(deleted);
    }
}
