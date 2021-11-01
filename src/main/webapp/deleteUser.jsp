<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<h1>Delete User</h1>

<form method="post" action="deleteUserServlet">
    <table>
        <tr>
            <td>Email:</td>
            <td><input name="email" required placeholder="Enter email"
                       oninvalid="this.setCustomValidity('Please enter Email')"
                       oninput="this.setCustomValidity('')"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input name="password" type="password" required placeholder="Enter password"
                       oninvalid="this.setCustomValidity('Please enter Password')"
                       oninput="this.setCustomValidity('')"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>

</form>
<br>

<h1>Delete All Users</h1>
<form method="post" action="deleteUserServlet">
    <table>
        <tr>
            <td>Admin Password:</td>
            <td><input name="adminPassword" type="password" required placeholder="Enter admin password"
                       oninvalid="this.setCustomValidity('Please enter Admin Password')"
                       oninput="this.setCustomValidity('')"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
    <br>
    <button><a href="index.jsp">Back</a></button>
    <br>
    <p>${userNotDeleted}</p>
    <p>${usersNotDeleted}</p>
</form>

</body>
</html>
