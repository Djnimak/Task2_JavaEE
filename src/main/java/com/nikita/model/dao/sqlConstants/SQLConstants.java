package com.nikita.model.dao.sqlConstants;

public interface SQLConstants {

    String CREATE_USER = "INSERT INTO user (id, firstName, lastName, email, password, gender, age) VALUES(DEFAULT,?,?,?,?,?,?)";
    String UPDATE_USER_EMAIL = "UPDATE user SET email = ?, password = ? WHERE email = ? AND password = ?";
    String SHOW_USER_EMAIL = "SELECT * FROM user WHERE email = ?";
    String SHOW_ALL_USERS = "SELECT * FROM user";
    String DELETE_USER_EMAIL = "DELETE FROM user WHERE email = ? AND password = ?";
    String DELETE_ALL_USERS = "DELETE FROM user";
    
}
