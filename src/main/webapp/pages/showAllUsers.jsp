<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>UserList</title>
    <style type="text/css">
        body{
            background-color: aquamarine;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<table align="center">

    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Family</th>
        <th>Role</th>
        <th>Email Address </th>
        <th>Manager Verified </th>
    </tr>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.family}</td>
            <td>${user.userRole}</td>
            <td>${user.emailAddress}</td>
            <td>${user.status}</td>
        </tr>
    </c:forEach>
</table>


<p align="center"><a href="/editUser">Edit User</a></p>
<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>
