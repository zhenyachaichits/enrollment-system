package com.epam.finaltask.university.dao;

import com.epam.finaltask.university.bean.Profile;
import com.epam.finaltask.university.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 08.02.2016.
 */
public interface ProfileDao extends EntityDao<String, Profile> {
    List<Profile> findByLastName(String lastName) throws DaoException;
    Profile findById(long id) throws DaoException;
}
