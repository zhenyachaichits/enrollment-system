package com.epam.finaltask.university.test;


import com.epam.finaltask.university.bean.Terms;
import com.epam.finaltask.university.dao.TermsDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlTermsDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class TermsDaoTest {

    private static final int YEAR = 2016;
    private static final int MONTH = 2;
    private static final int DAY = 5;

    private static TermsDao dao;
    private static Terms testTerms;

    private static final String CREATE_TEST_TERMS_QUERY = "INSERT INTO terms (start_date, end_date) VALUES " +
            "('2016-03-05', '2016-03-05')";
    private static final String DELETE_TEST_TERMS_QUERY = "DELETE FROM terms WHERE start_date = '2016-03-05' " +
            "AND end_date = '2016-03-05'";

    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = SqlTermsDaoImpl.getInstance();

        testTerms = new Terms();

        Calendar calendar = new GregorianCalendar(YEAR, MONTH, DAY);
        testTerms.setStartDate(calendar);
        testTerms.setEndDate(calendar);
    }

    @AfterClass
    public static void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
    }

    @Before
    public void createRecord() throws ConnectionPoolException, SQLException {
        long id = TestHelper.executeDBAction(CREATE_TEST_TERMS_QUERY);
        testTerms.setId(id);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_TERMS_QUERY);
    }


    @Test
    public void testAdd() throws DaoException {
        Terms added = dao.add(testTerms);

        assertNotNull(added);
    }

    @Test
    public void testFind() throws DaoException {
        Terms found = dao.find(testTerms.getId());

        assertNotNull(found);
        assertEquals(testTerms, found);
    }

    @Test
    public void testCheckExistence() throws DaoException {
        boolean result = dao.checkExistence(testTerms);

        assertTrue(result);
    }

    @Test
    public void testCheckUpdateAvailability() throws DaoException {
        boolean result = dao.checkUpdateAvailability(testTerms);

        assertTrue(result);
    }

    @Test
    public void testUpdate() throws DaoException {
        Terms updated = dao.update(testTerms);

        assertNotNull(updated);
        assertEquals(testTerms, updated);
    }

    @Test
    public void testAll() throws DaoException {
        List<Terms> termsList = dao.all();

        assertTrue(termsList.contains(testTerms));
    }

    @Test
    public void testDelete() throws DaoException {
        Terms deleted = dao.delete(testTerms.getId());

        assertNotNull(deleted);
    }
}
