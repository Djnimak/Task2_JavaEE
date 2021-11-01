package com.nikita.WebApplication;

import com.nikita.WebApplication.constants.Constants;
import com.nikita.WebApplication.entity.User;
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
import java.util.ArrayList;
import java.util.List;

public class ShowUserServlet extends HttpServlet {
    private Connection connection;
    private PreparedStatement statement;

    @Override
    public void init(ServletConfig config) {
        try {
            ServletContext sc = config.getServletContext();
            Class.forName(Constants.DB_DRIVER);
            connection = DriverManager.getConnection(sc.getInitParameter(Constants.DB_URL), sc.getInitParameter(Constants.DB_USER),
                    sc.getInitParameter(Constants.DB_PASS));
            statement = connection.prepareStatement(SQLConstants.SHOW_USER_EMAIL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(Constants.EMAIL);
        String command = request.getParameter(Constants.SHOW_ALL_USERS);
        RequestDispatcher requestDispatcher;
        try (Statement statement1 = connection.createStatement()){
            if (command == null) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                getUserList(request, resultSet);
            } else {
                ResultSet set = statement1.executeQuery(SQLConstants.SHOW_ALL_USERS);
                getUserList(request, set);
            }

            requestDispatcher = request.getRequestDispatcher(Constants.USER_BY_EMAIL_JSP);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            request.setAttribute(Constants.NO_SUCH_USER, Constants.NO_SUCH_USER_MESSAGE);
            requestDispatcher = request.getRequestDispatcher(Constants.SHOW_JSP);
            requestDispatcher.include(request, response);
            throwables.printStackTrace();
        }
    }

    private void getUserList(HttpServletRequest request, ResultSet resultSet) throws SQLException {
        List<User> result = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setGender(resultSet.getString(6));
            user.setAge(Integer.parseInt(resultSet.getString(7)));
            result.add(user);
        }
        System.out.println(result);
        request.setAttribute(Constants.USER, result);
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
