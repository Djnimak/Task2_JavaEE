<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h1>Create User</h1>
<form method="post" action="createUserServlet">
    <table>
        <tr>
            <td>First Name:</td>
            <td><input name="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input name="lastName"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input name="email"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input name="password" type="password"/></td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td><input type="radio" id="male" name="gender" value="Male"/>
                <label for="male">Male</label><br>
                <input type="radio" id="female" name="gender" value="Female"/>
                <label for="female">Female</label>
            </td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><input name="age"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </table>
    <br>
    <button><a href="index.jsp">Back</a></button>
    <br>
    <p>${userNotCreated}</p>
</form>
</body>
</html>
