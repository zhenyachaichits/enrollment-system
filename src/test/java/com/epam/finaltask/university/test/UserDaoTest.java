package com.epam.finaltask.university.test;


import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.bean.type.UserType;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {

    private static UserDao dao;
    private static User testUser;

    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "test";
    private static final String UPDATED_PASSWORD = "upd_test";
    private static final int OFFSET = 0;

    @BeforeClass
    public static void init() throws ConnectionPoolException, DaoFactoryException, DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init();

        dao = DaoFactory.getDaoFactory().getUserDao();

        testUser = new User();
        testUser.setEmail(EMAIL);
        testUser.setPassword(PASSWORD);
        testUser.setRole(UserType.SUPPORT);
    }

    @Test
    public void testAdd() throws DaoException {
        testUser = dao.add(testUser);

        assertNotNull(testUser);
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
        assertEquals(testUser, found);
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

    @Before
    public void beforeTestUpdate() {
        testUser.setPassword(UPDATED_PASSWORD);
    }

    @Test
    public void testUpdate() throws DaoException {
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


    @AfterClass
    public static void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.destroy();
    }

}
