<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Delete Course</title>
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
<form:form modelAttribute="courseDto" action="choiceCourseProcess" method="GET">
    <div align="center">
        <h3 style="color:darkgreen ">Course List</h3>
    </div>
    <table align="center">

        <tr>
            <th>Course Id</th>
            <th>Course Title</th>
            <th>Course Classification</th>
            <th>Course Members</th>
        </tr>

        <c:forEach items="${courseList}" var="course"  >
            <tr>
                <td>${course.id}</td>
                <td>${course.courseTitle}</td>
                <td>${course.courseClassification}</td>
                <td> <c:forEach items="${course.users}" var="user"  >
                    ${user.name}
                    ${user.family}
                </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
<div align="center">
    <h3 style="color: crimson">Please enter the Id of chosen course</h3>
</div>

    </table>
    <table align="center">
        <tr>
            <td>
                <form:label path="courseId">Course Id</form:label>
            </td>
            <td>
                <form:input path="courseId" name="courseId" />
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