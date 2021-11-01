package com.nikita.model.dao.impl;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.UserDao;


public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao();
    }

}
