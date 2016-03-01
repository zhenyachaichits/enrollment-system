package com.epam.finaltask.university.dao.util.bean.factory.impl;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.util.bean.factory.DaoBeanFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zheny Chaichits on 26.02.2016.
 */
public class FacultyDaoBeanFactory implements DaoBeanFactory<Faculty> {

    private FacultyDaoBeanFactory() { }

    public static class FacultyDaoConstructorHolder {
        public static final FacultyDaoBeanFactory INSTANCE = new FacultyDaoBeanFactory();
    }

    public static FacultyDaoBeanFactory getInstance() {
        return FacultyDaoConstructorHolder.INSTANCE;
    }

    private static final String ID_KEY = "faculty_id";
    private static final String NAME_KEY = "name";
    private static final String FREE_QUOTA_KEY = "free_quota";
    private static final String PAID_QUOTA_KEY = "paid_quota";
    private static final String FREE_POINT_KEY = "free_point";
    private static final String PAID_POINT_KEY = "paid_point";
    private static final String TERMS_KEY = "terms_terms_id";
    private static final String SUBJECT_ID_KEY = "subject_subject_id";

    @Override
    public Faculty construct(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty();

        long facultyId = resultSet.getLong(ID_KEY);
        faculty.setId(facultyId);
        faculty.setName(resultSet.getString(NAME_KEY));
        faculty.setFreeQuota(resultSet.getInt(FREE_QUOTA_KEY));
        faculty.setPaidQuota(resultSet.getInt(PAID_QUOTA_KEY));
        faculty.setFreePoint(resultSet.getInt(FREE_POINT_KEY));
        faculty.setPaidPoint(resultSet.getInt(PAID_POINT_KEY));
        faculty.setTermsId(resultSet.getLong(TERMS_KEY));

        Set<Long> subjects = new HashSet<>();
        subjects.add(resultSet.getLong(SUBJECT_ID_KEY));
        do {
            if(facultyId == resultSet.getLong(ID_KEY)) {
                subjects.add(resultSet.getLong(SUBJECT_ID_KEY));
            } else {
                resultSet.previous();
                break;
            }
        } while (resultSet.next());

        faculty.setSubjects(subjects);

        return faculty;
    }
}
