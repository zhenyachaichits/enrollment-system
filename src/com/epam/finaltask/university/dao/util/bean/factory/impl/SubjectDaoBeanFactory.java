package com.epam.finaltask.university.dao.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Zheny Chaichits on 27.02.2016.
 */
public class SubjectDaoBeanFactory implements DaoBeanFactory<Subject>{

    private SubjectDaoBeanFactory() { }

    public static class SubjectDaoBeanFactoryHolder {
        public static final SubjectDaoBeanFactory INSTANCE = new SubjectDaoBeanFactory();
    }

    public static SubjectDaoBeanFactory getInstance() {
        return SubjectDaoBeanFactoryHolder.INSTANCE;
    }

    private static final String ID_KEY = "subject_id";
    private static final String NAME_KEY = "name";
    private static final String MIN_POINTS_KEY = "min_points";

    @Override
    public Subject construct(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();

        subject.setId(resultSet.getLong(ID_KEY));
        subject.setName(resultSet.getString(NAME_KEY));
        subject.setMinPoint(resultSet.getInt(MIN_POINTS_KEY));

        return subject;
    }
}
