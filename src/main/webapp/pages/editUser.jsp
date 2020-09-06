<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>UserEdit</title>
    <style>
        body {
            background-color: lightskyblue;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        .button {
            background-color: seashell;
            border: none;
            color: black;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<div align="center">
    <h3 style="color:darkgreen "> The User List</h3>
</div>
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

<div align="center">
    <h3 style="color:darkgreen "> Enter the id of the user first</h3>
</div>

<form:form modelAttribute="user" action="editUserInfoProcess" method="GET">
    <table align="center">
        <tr>
            <td>
                <form:label path="id">Id</form:label>
            </td>
            <td>
                <form:input path="id" name="id" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userRole">User Role</form:label>
            </td>
            <td>
                <form:radiobutton path="userRole" name="userRole" value="student"/> Student<br>

                <form:radiobutton path="userRole" name="userRole" value="teacher"/> Teacher
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="status">Status</form:label>
            </td>
            <td>
                <form:radiobutton path="status" name="status" value="unverified"/>not verified<br>

                <form:radiobutton path="status" name="status" value="verified"/>verified
            </td>
        </tr>
    </table>
    <div align="center">
        <p>Submit or clear the change<br>
        <input type="reset" value="Clear"/>

                <form:button name="submit">Submit</form:button>
        </p>
    </div>
</form:form>

<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>