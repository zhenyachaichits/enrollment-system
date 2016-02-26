package com.epam.finaltask.university.service;

import com.epam.finaltask.university.bean.Subject;
import com.epam.finaltask.university.dao.SubjectDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Zheny Chaichits on 15.02.2016.
 */
public class SubjectService {

    private SubjectService() { }

    public static class SubjectServiceHolder {
        public static final SubjectService INSTANCE = new SubjectService();
    }

    public static SubjectService getInstance() {
        return SubjectServiceHolder.INSTANCE;
    }

    public List<Subject> getSubjectsByFacultyId(long facultyId) throws ServiceException {
        try {
            SubjectDao dao = DaoFactory.getDaoFactory().getSubjectDao();

            return dao.getSubjectsByFacultyId(facultyId);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide subjects finding service", e);
        }
    }
}
