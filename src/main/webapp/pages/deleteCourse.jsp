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

<div align="center">
    <h3 style="color: darkblue ">Course List</h3>
</div>
<table align="center">

    <tr>
        <th>Course Id</th>
        <th>Course Title</th>
        <th>Course Classification</th>
    </tr>

    <c:forEach items="${courseList}" var="course">
    <tr>
        <td>${course.id}</td>
        <td>${course.courseTitle}</td>
        <td>${course.courseClassification}</td>
    </tr>
    </c:forEach>
</table>
    <div align="center">
        <h3 style="color: darkblue ">Choose id or title of the course you want deleted</h3>
    </div>
    <form:form modelAttribute="course" action="deleteCourseProcess" method="GET">
    <table align="center">
        <tr>
            <td>
                <form:label path="id">Id</form:label>
            </td>
            <td>
                <form:input path="id" name="id"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="courseTitle">Course Title</form:label>
            </td>
            <td>
                <form:input path="courseTitle" name="courseTitle"/>
            </td>
        </tr>
    </table>
    <div align="center">
       <p>
        <input type="reset" value="Clear"/>

        <form:button name="submit">Submit</form:button>
        </p>
    </div>

    </form:form>


    <p align="center"><a href="/">Home</a></p>
    <p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>