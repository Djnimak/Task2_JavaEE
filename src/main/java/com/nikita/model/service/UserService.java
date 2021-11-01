package com.nikita.model.service;

import com.nikita.model.dao.DaoFactory;
import com.nikita.model.dao.UserDao;
import com.nikita.model.entity.User;

import java.sql.Connection;
import java.util.List;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public int createUser(User user, Connection connection) {
            UserDao dao = daoFactory.createUserDao();
            return dao.create(user, connection);
    }

    public int updateUser(User user, String newEmail, String newPassword, Connection connection) {
            UserDao dao = daoFactory.createUserDao();
            return dao.update(user, newEmail, newPassword, connection);
    }

    public int deleteUser(User user, Connection connection) {
            UserDao dao = daoFactory.createUserDao();
            return dao.delete(user, connection);
    }

    public int deleteAllUsers(Connection connection) {
            UserDao dao = daoFactory.createUserDao();
            return dao.deleteAll(connection);
    }

    public List<User> findUserByEmail(String email, Connection connection) {
            UserDao dao = daoFactory.createUserDao();
            return dao.findByEmail(email, connection);
    }

    public List<User> findAllUsers(Connection connection) {
            UserDao dao = daoFactory.createUserDao();
            return dao.findAll(connection);
    }
}
