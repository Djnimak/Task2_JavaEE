package com.nikita.model.dao;

import com.nikita.model.dao.impl.JDBCDaoFactory;


public abstract class DaoFactory {
    private static DaoFactory daoFactory;
    public abstract UserDao createUserDao();

    public static DaoFactory getInstance() {
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}