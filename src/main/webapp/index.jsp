<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task2_JavaEE</title>
</head>
<body>
<h1>CRUD Operations Using Servlets</h1>
<br/>

<ol>
    <li><a href="createUser.jsp">Create User</a></li>
    <li><a href="updateUser.jsp">Update User</a></li>
    <li><a href="showUser.jsp">Show User</a></li>
    <li><a href="deleteUser.jsp">Delete User</a></li>
</ol>
<br>
<p>${userCreated}</p>
<p>${userUpdated}</p>
<p>${userDeleted}</p>
<p>${allUsersDeleted}</p>


</body>
</html>