<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>StudentPanel</title>
    <style>
        body {
            background-color: gold;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<div>
    <p>${user.name} ${user.family}, welcome to student panel!</p>
</div>

<div align="center">
    <h2>
        <br><br><label style="color: darkblue"> ${message} </label><br><br>
    </h2>

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

<div align="center">

    <form:form modelAttribute="course" action="selectCourseToSeeExams" method="GET">

        <div align="center">
            <br><br><h3>Select Course</h3>
            <p>${user.name}, select the course to see the list of exams </p>
        </div>
        <table align="center">
            <tbody>
            <tr>
                <td>
                    <form:select path="courseTitle" style="width:200px">
                        <form:option value="NONE" label="Select"/>
                        <form:options  items="${courseTitles}" />
                    </form:select>
                </td>
                <td>
                    <form:button name="submit">Select</form:button>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <br><br><br>
</div>

<p align="center"><a href="/totalPreviousExamsScore">Previous Exam Report</a></p>
<p align="center"><a href="/">Home</a></p>

</body>
</html>