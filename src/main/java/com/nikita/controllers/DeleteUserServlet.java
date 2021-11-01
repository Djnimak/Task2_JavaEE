package com.nikita.controllers;

import com.nikita.constants.Constants;
import com.nikita.model.entity.User;
import com.nikita.model.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteUserServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init(ServletConfig config) {
        try {
            ServletContext sc = config.getServletContext();
            Class.forName(Constants.DB_DRIVER);
            connection = DriverManager.getConnection(sc.getInitParameter(Constants.DB_URL), sc.getInitParameter(Constants.DB_USER),
                    sc.getInitParameter(Constants.DB_PASS));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setEmail(request.getParameter(Constants.EMAIL));
        user.setPassword(request.getParameter(Constants.PASSWORD));
        String adminPassword = request.getParameter(Constants.ADMIN_PASSWORD);
        RequestDispatcher requestDispatcher;
        if (adminPassword == null) {
            try {
                UserService userService = new UserService();
                int result = userService.deleteUser(user, connection);
                requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
                if (result > 0) {
                    request.setAttribute(Constants.USER_DELETED, Constants.USER_DELETED_MESSAGE);
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute(Constants.USER_NOT_DELETED, Constants.USER_NOT_DELETED_MESSAGE);
                    requestDispatcher = request.getRequestDispatcher(Constants.DELETE_JSP);
                    requestDispatcher.include(request, response);
                }
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            if (adminPassword.equals(Constants.ADMIN_PASSWORD_VALUE)) {
                UserService userService = new UserService();
                int result = userService.deleteAllUsers(connection);
                requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
                if (result > 0) {
                    request.setAttribute(Constants.ALL_USERS_DELETED, Constants.ALL_USERS_DELETED_MESSAGE);
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute(Constants.USERS_NOT_DELETED, Constants.USERS_NOT_DELETED_MESSAGE);
                    requestDispatcher = request.getRequestDispatcher(Constants.DELETE_JSP);
                    requestDispatcher.include(request, response);
                }
            } else {
                request.setAttribute(Constants.USERS_NOT_DELETED, Constants.USERS_NOT_DELETED_MESSAGE);
                requestDispatcher = request.getRequestDispatcher(Constants.DELETE_JSP);
                requestDispatcher.include(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
