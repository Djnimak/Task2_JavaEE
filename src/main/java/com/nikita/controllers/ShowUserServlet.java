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
import java.sql.*;
import java.util.List;

public class ShowUserServlet extends HttpServlet {
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
        String email = request.getParameter(Constants.EMAIL);
        String command = request.getParameter(Constants.SHOW_ALL_USERS);
        RequestDispatcher requestDispatcher;
        try {
            UserService userService = new UserService();
            List<User> list;
            if (command == null) {
                list = userService.findUserByEmail(email, connection);
            } else {
                list = userService.findAllUsers(connection);
            }
            request.setAttribute(Constants.USER, list);

            requestDispatcher = request.getRequestDispatcher(Constants.USER_BY_EMAIL_JSP);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException throwables) {
            request.setAttribute(Constants.NO_SUCH_USER, Constants.NO_SUCH_USER_MESSAGE);
            requestDispatcher = request.getRequestDispatcher(Constants.SHOW_JSP);
            requestDispatcher.include(request, response);
            throwables.printStackTrace();
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
