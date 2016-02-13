package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 24.01.16.
 * @author
 *
 */

public interface EntityDao<K, T> {
    T add(T entity) throws DaoException;
    T find(K domain) throws DaoException;
    T update(T entity) throws DaoException;
    T delete(K domain) throws DaoException;
    List<T> all() throws DaoException;
}

