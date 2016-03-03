package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * The interface Entity dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface EntityDao<K, T> {
    /**
     * Add T.
     *
     * @param entity the entity
     * @return the t
     * @throws DaoException the dao exception
     */
    T add(T entity) throws DaoException;

    /**
     * Find T.
     *
     * @param domain the domain
     * @return the t
     * @throws DaoException the dao exception
     */
    T find(K domain) throws DaoException;

    /**
     * Update T.
     *
     * @param entity the entity
     * @return the t
     * @throws DaoException the dao exception
     */
    T update(T entity) throws DaoException;

    /**
     * Delete T.
     *
     * @param domain the domain
     * @return the t
     * @throws DaoException the dao exception
     */
    T delete(K domain) throws DaoException;

    /**
     * All list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> all() throws DaoException;
}

