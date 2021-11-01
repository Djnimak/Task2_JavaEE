package com.nikita.model.dao;

import com.nikita.model.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    int create(User user, Connection connection);
    List<User> findByEmail(String email, Connection connection);
    List<User> findAll(Connection connection);
    int update(User user,String newEmail, String newPassword, Connection connection);
    int delete(User user, Connection connection);
    int deleteAll(Connection connection);
}
