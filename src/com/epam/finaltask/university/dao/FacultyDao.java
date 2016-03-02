package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.exception.DaoException;

/**
* Created by Zheny Chaichits on 08.02.2016.
        */
public interface FacultyDao extends EntityDao<String, Faculty> {
    Faculty find(long facultyId) throws DaoException;

    boolean checkUpdateAvailability(Faculty faculty) throws DaoException;

    boolean delete(long facultyId) throws DaoException;
}
