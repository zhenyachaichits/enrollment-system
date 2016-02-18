package com.epam.finaltask.university.service.impl;

import com.epam.finaltask.university.bean.Student;
import com.epam.finaltask.university.bean.User;
import com.epam.finaltask.university.dao.StudentDao;
import com.epam.finaltask.university.dao.UserDao;
import com.epam.finaltask.university.dao.exception.DaoException;
import com.epam.finaltask.university.dao.exception.DaoFactoryException;
import com.epam.finaltask.university.dao.factory.DaoFactory;
import com.epam.finaltask.university.service.Service;
import com.epam.finaltask.university.service.exception.ServiceException;
import com.epam.finaltask.university.service.util.DataEncrypter;

/**
 * Created by Zheny Chaichits on 18.02.2016.
 */
public class StudentService implements Service {

    private StudentService() { }

    public static class StudentServiceHolder {
        public static final StudentService INSTANCE = new StudentService();
    }

    public static StudentService getInstance() {
        return StudentServiceHolder.INSTANCE;
    }

    public boolean checkStudentExistence(Student student) throws ServiceException {
        try {
            StudentDao dao = DaoFactory.getDaoFactory().getStudentDao();

            return dao.checkStudentExistence(student);
        } catch (DaoException | DaoFactoryException e) {
            throw new ServiceException("Couldn't provide student existence checking service");
        }
    }

}
