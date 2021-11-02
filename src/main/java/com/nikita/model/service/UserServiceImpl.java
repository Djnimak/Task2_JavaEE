package com.nikita.model.service;

import com.nikita.model.dao.UserDao;
import com.nikita.model.dao.impl.JDBCUserDao;
import com.nikita.model.entity.User;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService{

    UserDao dao = new JDBCUserDao();


    public int createUser(User user, Connection connection) {
            return dao.create(user, connection);
    }

    public int updateUser(User user, String newEmail, String newPassword, Connection connection) {
            return dao.update(user, newEmail, newPassword, connection);
    }

    public int deleteUser(User user, Connection connection) {
            return dao.delete(user, connection);
    }

    public int deleteAllUsers(Connection connection) {
            return dao.deleteAll(connection);
    }

    public List<User> findUserByEmail(String email, Connection connection) {
            return dao.findByEmail(email, connection);
    }

    public List<User> findAllUsers(Connection connection) {
            return dao.findAll(connection);
    }
}
