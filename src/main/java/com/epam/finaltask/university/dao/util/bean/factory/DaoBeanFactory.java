package com.epam.finaltask.university.dao.util.bean.factory;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The interface Dao bean factory.
 *
 * @param <T> the type parameter
 */
public interface DaoBeanFactory<T> {
    /**
     * Construct t.
     *
     * @param resultSet the result set
     * @return the T object reference
     * @throws SQLException the sql exception
     */
    T construct(ResultSet resultSet) throws SQLException;
}
