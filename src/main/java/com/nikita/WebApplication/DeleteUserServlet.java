package com.nikita.WebApplication;

import com.nikita.WebApplication.constants.Constants;
import com.nikita.WebApplication.sqlConstants.SQLConstants;

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
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserServlet extends HttpServlet {
    private Connection connection;
    private PreparedStatement statementDelete;
    private PreparedStatement statementDeleteAll;

    @Override
    public void init(ServletConfig config) {
        try {
            ServletContext sc = config.getServletContext();
            Class.forName(Constants.DB_DRIVER);
            connection = DriverManager.getConnection(sc.getInitParameter(Constants.DB_URL), sc.getInitParameter(Constants.DB_USER),
                    sc.getInitParameter(Constants.DB_PASS));
            statementDelete = connection.prepareStatement(SQLConstants.DELETE_USER_EMAIL);
            statementDeleteAll = connection.prepareStatement(SQLConstants.DELETE_ALL_USERS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        String adminPassword = request.getParameter(Constants.ADMIN_PASSWORD);
        RequestDispatcher requestDispatcher;
        if (adminPassword == null) {
            try {
                statementDelete.setString(1, email);
                statementDelete.setString(2, password);
                int result = statementDelete.executeUpdate();
                requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
                if (result > 0) {
                    request.setAttribute(Constants.USER_DELETED, Constants.USER_DELETED_MESSAGE);
                    requestDispatcher.forward(request, response);
                }
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                request.setAttribute(Constants.USER_NOT_DELETED, Constants.USER_NOT_DELETED_MESSAGE);
                requestDispatcher = request.getRequestDispatcher(Constants.DELETE_JSP);
                requestDispatcher.include(request, response);
                throwables.printStackTrace();
            }
        } else {
            if (adminPassword.equals(Constants.ADMIN_PASSWORD_VALUE)) {
                try {
                    int result = statementDeleteAll.executeUpdate();
                    requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
                    if (result > 0) {
                        request.setAttribute(Constants.ALL_USERS_DELETED, Constants.ALL_USERS_DELETED_MESSAGE);
                        requestDispatcher.forward(request, response);
                    }
                } catch (SQLException throwables) {
                    request.setAttribute(Constants.USERS_NOT_DELETED, Constants.USERS_NOT_DELETED_MESSAGE);
                    requestDispatcher = request.getRequestDispatcher(Constants.DELETE_JSP);
                    requestDispatcher.include(request, response);
                    throwables.printStackTrace();
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
