<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Fill Course</title>
    <style>
        body {
            background-color: lightskyblue;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<form:form modelAttribute="courseDto" action="addUserProcess" method="GET">
    <div align="center">
        <h3 style="color:darkgreen ">The selected course</h3>
    </div>

    <table align="center">
        <tr>
            <th>Course Id</th>
            <th>Course Title</th>
            <th>Course Classification</th>
            <th>Course Members</th>
        </tr>

        <c:forEach items="${courseList}" var="course">
            <tr>
                <td>${course.id}</td>
                <td>${course.courseTitle}</td>
                <td>${course.courseClassification}</td>
                <td><c:forEach items="${course.users}" var="user">
                    ${user.name}
                    ${user.family}
                </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        <h3 style="color:darkgreen ">User List</h3>
    </div>

    <table align="center">

        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Family</th>
            <th>Role</th>
            <th>Email Address</th>
            <th>Manager Verified</th>
        </tr>
        <c:forEach items="${userList}" var="user">
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
        <h3 style="color: crimson ">If you want to add a member, enter the ID.</h3>
    </div>
    <table align="center">
        <tr>
            <td>
                <form:input type="hidden" path="courseId" name="id"/>

                <form:label path="userId">User Id</form:label>

                <form:input path="userId" name="userId"/>
            </td>
            <td>
                <form:button name="submit">Submit</form:button>
            </td>
        </tr>

        <tr></tr>

    </table>
</form:form>


<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>