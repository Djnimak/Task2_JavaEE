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
import java.sql.*;

public class CreateUserServlet extends HttpServlet {
    private Connection connection;
    private PreparedStatement statement;

    @Override
    public void init(ServletConfig config) {
        try {
            ServletContext sc = config.getServletContext();
            Class.forName(Constants.DB_DRIVER);
            connection = DriverManager.getConnection(sc.getInitParameter(Constants.DB_URL), sc.getInitParameter(Constants.DB_USER),
                    sc.getInitParameter(Constants.DB_PASS));
            statement = connection.prepareStatement(SQLConstants.CREATE_USER);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter(Constants.FIRST_NAME);
        String lastName = request.getParameter(Constants.LAST_NAME);
        String email = request.getParameter(Constants.EMAIL);
        String password = request.getParameter(Constants.PASSWORD);
        String gender = request.getParameter(Constants.GENDER);
        int age = 0;
        try {
            age = Integer.parseInt(request.getParameter(Constants.AGE));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher;
        try {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, gender);
            statement.setInt(6, age);
            int result = statement.executeUpdate();
            requestDispatcher = request.getRequestDispatcher(Constants.INDEX_JSP);
            if (result > 0) {
                request.setAttribute(Constants.USER_CREATED, Constants.USER_CREATED_MESSAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {

            e.printStackTrace();
        } catch (SQLException throwables) {
            request.setAttribute(Constants.USER_NOT_CREATED, Constants.USER_NOT_CREATED_MESSAGE);
            requestDispatcher = request.getRequestDispatcher(Constants.CREATE_JSP);
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
