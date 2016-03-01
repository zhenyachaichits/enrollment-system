package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public interface SubjectDao extends EntityDao<String, Subject> {
    List<Subject> getSubjectsByFacultyId(long facultyId) throws DaoException;

    Subject getSubjectById(long id) throws DaoException;

    boolean delete(long id) throws DaoException;

    boolean checkUpdateAvailability(Subject subject) throws DaoException;
}
