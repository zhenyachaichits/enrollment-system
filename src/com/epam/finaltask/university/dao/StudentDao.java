package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Student;
import com.epam.finaltask.university.dao.exception.DaoException;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public interface StudentDao extends EntityDao<String, Student> {
    boolean checkStudentExistence(Student student) throws DaoException;
}
