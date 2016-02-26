package com.epam.finaltask.university.dao.util.constructor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public interface DaoConstructor<T> {
    T construct(ResultSet resultSet) throws SQLException;
}
