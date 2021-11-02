package com.nikita.controllers;

import com.nikita.constants.Constants;
import com.nikita.model.entity.User;
import com.nikita.model.service.UserService;
import com.nikita.model.service.UserServiceImpl;

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

public class UpdateUserServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setEmail(request.getParameter(Constants.EMAIL));
        user.setPassword(request.getParameter(Constants.PASSWORD));
        String newEmail = request.getParameter(Constants.NEW_EMAIL);
        String newPassword = request.getParameter(Constants.NEW_PASSWORD);
        RequestDispatcher requestDispatcher;
        try {
            UserService userService = new UserServiceImpl();
            int result = userService.updateUser(user, newEmail, newPassword, connection);
            requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
            if (result > 0) {
                request.setAttribute(Constants.USER_UPDATED, Constants.USER_UPDATED_MESSAGE);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(Constants.USER_NOT_UPDATED, Constants.USER_NOT_UPDATED_MESSAGE);
                requestDispatcher = request.getRequestDispatcher(Constants.UPDATE_JSP);
                requestDispatcher.include(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
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
