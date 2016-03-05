package com.epam.finaltask.university.test;


import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.impl.SqlUserDaoImpl;
import com.epam.finaltask.university.test.helper.TestHelper;
import org.junit.*;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    private static UserDao dao;
    private static User testUser;

    private static final String EMAIL = "test";
    private static final String PASSWORD = "test";
    private static final String UPDATED_PASSWORD = "upd_test";
    private static final int OFFSET = 0;

    private static final String CREATE_TEST_USER_QUERY = "INSERT INTO user (email, password_hash, role) VALUES " +
            "('test', 'test', 'SUPPORT')";
    private static final String DELETE_TEST_USER_QUERY = "DELETE FROM user WHERE email = 'test'";

    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = SqlUserDaoImpl.getInstance();

        testUser = new User();
        testUser.setEmail(EMAIL);
        testUser.setPassword(PASSWORD);
        testUser.setRole(UserType.SUPPORT);
    }

    @AfterClass
    public static void destroy() throws ConnectionPoolException, SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.destroy();
    }

    @Before
    public void createRecord() throws ConnectionPoolException, SQLException {
        long id = TestHelper.executeDBAction(CREATE_TEST_USER_QUERY);
        testUser.setId(id);
    }

    @After
    public void deleteRecord() throws ConnectionPoolException, SQLException {
        TestHelper.executeDBAction(DELETE_TEST_USER_QUERY);
    }

    @Test
    public void testAdd() throws DaoException {
        User added = dao.add(testUser);

        assertNotNull(added);
    }

    @Test
    public void testFind() throws DaoException {
        User found = dao.find(testUser.getEmail());

        assertNotNull(found);
        assertEquals(testUser.getEmail(), found.getEmail());
    }

    @Test
    public void testFindUserToLogIn() throws DaoException {
        User found = dao.findUserToLogIn(testUser.getEmail(), testUser.getPassword());

        assertNotNull(found);
        assertEquals(testUser, found);
    }

    @Test
    public void testFindUserById() throws DaoException {
        User found = dao.findUserById(testUser.getId());

        assertNotNull(found);
    }

    @Test
    public void testFindUsersByRole() throws DaoException {
        int maxCount = (int) testUser.getId();
        List<User> users = dao.findUsersByRole(testUser.getRole(), OFFSET, maxCount);

        assertTrue(users.contains(testUser));
    }

    @Test
    public void testCheckUpdateAvailability() throws DaoException {
        boolean result = dao.checkUpdateAvailability(testUser);

        assertTrue(result);
    }

    @Test
    public void testUpdate() throws DaoException {
        testUser.setPassword(UPDATED_PASSWORD);
        User updated = dao.update(testUser);

        assertNotNull(updated);
        assertEquals(testUser.getPassword(), updated.getPassword());
    }

    @Test
    public void testAll() throws DaoException {
        List<User> users = dao.all();

        assertTrue(users.contains(testUser));
    }

    @Test
    public void testDelete() throws DaoException {
        User deleted = dao.delete(testUser.getEmail());

        assertNotNull(deleted);
    }
}
