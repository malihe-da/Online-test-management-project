<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>TeacherPanel</title>
    <style>
        body {
            background-color: lightpink;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

    </style>
</head>
<body>


<div align="center">
    <h3 style="color: darkblue">${user.name} ${user.family}, welcome to teacher panel!</h3>
</div>
<div align="center">
    <h3 style="color:darkgreen ">Your Courses</h3>
</div>

<table align="center">
    <tr>
        <th>Course Id</th>
        <th>Course Title</th>
        <th>Course Classification</th>
    </tr>

    <c:forEach items="${user.courses}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.courseTitle}</td>
            <td>${course.courseClassification}</td>
        </tr>
    </c:forEach>


</table>
<p align="center"><a href="/allCourseExam">All Exam of a Selected Course</a></p>
<p align="center"><a href="/new-exam">New Exam</a></p>
<p align="center"><a href="/">Home</a></p>
</body>
</html>