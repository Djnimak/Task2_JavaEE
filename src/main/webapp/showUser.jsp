<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Users</title>
</head>
<body>
<h1>Show User</h1>
<form method="post" action="showUserServlet">
    <table>
        <tr>User Email:</tr>
        <td><input name="email"/></td>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form>

<br>
<form method="post" action="showUserServlet">
    <input type="submit" name="showAllUsers" value="Show All Users"/>
</form>

<br>
<button><a href="index.jsp">Back</a></button>
<br>

</body>
</html>
