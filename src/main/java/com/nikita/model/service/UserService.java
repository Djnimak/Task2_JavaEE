package com.nikita.model.service;

import com.nikita.model.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    int createUser(User user, Connection connection);
    int updateUser(User user, String newEmail, String newPassword, Connection connection);
    int deleteUser(User user, Connection connection);
    int deleteAllUsers(Connection connection);
    List<User> findUserByEmail(String email, Connection connection);
    List<User> findAllUsers(Connection connection);
}
