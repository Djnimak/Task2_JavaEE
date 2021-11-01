package com.nikita.constants;

public interface Constants {

    String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    String DB_URL = "dbUrl";
    String DB_USER = "dbUser";
    String DB_PASS = "dbPass";

    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String EMAIL = "email";
    String NEW_EMAIL = "newEmail";
    String PASSWORD = "password";
    String NEW_PASSWORD = "newPassword";
    String GENDER = "gender";
    String AGE = "age";

    String ADMIN_PASSWORD = "adminPassword";
    String ADMIN_PASSWORD_VALUE = "qwerty123";
    String USER = "user";
    String SHOW_ALL_USERS = "showAllUsers";

    String USER_CREATED = "userCreated";
    String USER_NOT_CREATED = "userNotCreated";
    String USER_CREATED_MESSAGE = "User was successfully created";
    String USER_NOT_CREATED_MESSAGE = "Failed to create a User. Please try again";

    String USER_UPDATED = "userUpdated";
    String USER_NOT_UPDATED = "userNotUpdated";
    String USER_UPDATED_MESSAGE = "User was successfully updated";
    String USER_NOT_UPDATED_MESSAGE = "Failed to update a User. Please try again";

    String USER_DELETED = "userDeleted";
    String USER_NOT_DELETED = "userNotDeleted";
    String USER_DELETED_MESSAGE = "User was successfully deleted";
    String USER_NOT_DELETED_MESSAGE = "Failed to delete a User. Please try again";

    String ALL_USERS_DELETED = "allUsersDeleted";
    String USERS_NOT_DELETED = "usersNotDeleted";
    String ALL_USERS_DELETED_MESSAGE = "Users were successfully deleted";
    String USERS_NOT_DELETED_MESSAGE = "Failed to delete Users. Please try again";

    String NO_SUCH_USER = "noSuchUser";
    String NO_SUCH_USER_MESSAGE = "There is not User with such an email";

    String INDEX_JSP = "index.jsp";
    String CREATE_JSP = "createUser.jsp";
    String UPDATE_JSP = "updateUser.jsp";
    String DELETE_JSP = "deleteUser.jsp";
    String SHOW_JSP = "showUser.jsp";
    String USER_BY_EMAIL_JSP = "userByEmail.jsp";


}
