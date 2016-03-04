package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.to.Student;
import com.epam.finaltask.university.dao.exception.DaoException;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public interface StudentDao extends EntityDao<String, Student> {
    /**
     * Check student existence.
     *
     * @param student the student
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkStudentExistence(Student student) throws DaoException;

    /**
     * Check update availability.
     *
     * @param student the student
     * @return is available boolean
     * @throws DaoException the dao exception
     */
    boolean checkUpdateAvailability(Student student) throws DaoException;

    /**
     * Delete student by user ID.
     *
     * @param userId the user id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(long userId) throws DaoException;
}
