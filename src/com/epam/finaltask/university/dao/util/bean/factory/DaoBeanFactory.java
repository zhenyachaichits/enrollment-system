package com.epam.finaltask.university.dao.util.bean.factory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 25.02.2016.
 */
public interface DaoBeanFactory<T> {
    T construct(ResultSet resultSet) throws SQLException;
}
