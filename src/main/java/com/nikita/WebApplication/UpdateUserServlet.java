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

public class UpdateUserServlet extends HttpServlet {
    private Connection connection;
    private PreparedStatement statement;

    @Override
    public void init(ServletConfig config) {
        try {
            ServletContext sc = config.getServletContext();
            Class.forName(Constants.DB_DRIVER);
            connection = DriverManager.getConnection(sc.getInitParameter(Constants.DB_URL), sc.getInitParameter(Constants.DB_USER),
                    sc.getInitParameter(Constants.DB_PASS));
            statement = connection.prepareStatement(SQLConstants.UPDATE_USER_EMAIL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        String newEmail = request.getParameter(Constants.NEW_EMAIL);
        String newPassword = request.getParameter(Constants.NEW_PASSWORD);
        RequestDispatcher requestDispatcher;
        try {
            statement.setString(1, newEmail);
            statement.setString(2, newPassword);
            statement.setString(3, email);
            statement.setString(4, password);
            int result = statement.executeUpdate();
            requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
            if (result > 0) {
                request.setAttribute(Constants.USER_UPDATED, Constants.USER_UPDATED_MESSAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            request.setAttribute(Constants.USER_NOT_UPDATED, Constants.USER_NOT_UPDATED_MESSAGE);
            requestDispatcher = request.getRequestDispatcher(Constants.UPDATE_JSP);
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
