package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Faculty;
import com.epam.finaltask.university.dao.exception.DaoException;


/**
 * The interface Faculty dao.
 */
public interface FacultyDao extends EntityDao<String, Faculty> {
    /**
     * Find faculty.
     *
     * @param facultyId the faculty id
     * @return the faculty
     * @throws DaoException the dao exception
     */
    Faculty find(long facultyId) throws DaoException;

    /**
     * Check update availability .
     *
     * @param faculty the faculty
     * @return update availability
     * @throws DaoException the dao exception
     */
    boolean checkUpdateAvailability(Faculty faculty) throws DaoException;

    /**
     * Delete faculty.
     *
     * @param facultyId the faculty id
     * @return is deleted
     * @throws DaoException the dao exception
     */
    boolean delete(long facultyId) throws DaoException;

    /**
     * Update passing points.
     *
     * @param facultyId the faculty id
     * @param freePoint the free point
     * @param paidPoint the paid point
     * @return is updated
     * @throws DaoException the dao exception
     */
    boolean updatePoints(long facultyId, int freePoint, int paidPoint) throws DaoException;

    boolean checkDeletionAvailability(long facultyId) throws DaoException;
}
