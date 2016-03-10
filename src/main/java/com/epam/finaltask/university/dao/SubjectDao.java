package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;


/**
 * The interface Subject dao.
 */
public interface SubjectDao extends EntityDao<String, Subject> {
    /**
     * Gets subjects by faculty id.
     *
     * @param facultyId the faculty id
     * @return the subjects by faculty id
     * @throws DaoException the dao exception
     */
    List<Subject> getSubjectsByFacultyId(long facultyId) throws DaoException;

    /**
     * Gets subject by id.
     *
     * @param id the id
     * @return the subject by id
     * @throws DaoException the dao exception
     */
    Subject getSubjectById(long id) throws DaoException;

    /**
     * Delete subject.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(long id) throws DaoException;

    /**
     * Check update availability.
     *
     * @param subject the subject
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean checkUpdateAvailability(Subject subject) throws DaoException;

    boolean checkDeletionAvailability(long subjectId) throws DaoException;
}
