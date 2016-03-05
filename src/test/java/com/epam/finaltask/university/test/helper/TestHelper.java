package com.epam.finaltask.university.test.helper;

import com.epam.finaltask.university.dao.connection.ConnectionPool;
import com.epam.finaltask.university.dao.connection.exception.ConnectionPoolException;

import java.sql.*;

/**
 * Created by Zheny Chaichits on 05.03.2016.
 */
public class TestHelper {
    public static long executeDBAction(String query) throws ConnectionPoolException, SQLException {
        long key = 0;

        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            key = generatedKeys.getLong(1);
        }
        connection.close();

        return key;
    }
}
