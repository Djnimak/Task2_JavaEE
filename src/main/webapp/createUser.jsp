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
            <td><input name="firstName" required placeholder="Enter First Name"
                       oninvalid="this.setCustomValidity('Please enter First Name')"
                       oninput="this.setCustomValidity('')"/></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><input name="lastName" required placeholder="Enter Last Name"
                       oninvalid="this.setCustomValidity('Please enter Last Name')"
                       oninput="this.setCustomValidity('')"/></td>
        </tr>
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
            <td>Gender:</td>
            <td><input type="radio" id="male" name="gender" value="Male" required
                       oninvalid="this.setCustomValidity('Please choose Gender')"
                       oninput="this.setCustomValidity('')"/>
                <label for="male">Male</label><br>
                <input type="radio" id="female" name="gender" value="Female"/>
                <label for="female">Female</label>
            </td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><input name="age" required placeholder="Enter age"
                       oninvalid="this.setCustomValidity('Please enter Age')"
                       oninput="this.setCustomValidity('')"/></td>
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
