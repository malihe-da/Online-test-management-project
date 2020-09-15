<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>StudentExamList</title>
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
    <p>${user.name} ${user.family} panel!</p>
</div>

<div align="center">
    <h3 style="color:darkgreen ">The ${selectedCourse.courseTitle} has following exams</h3>
</div>
<table align="center">
    <tr>
        <th> Title</th>
        <th> Description</th>
        <th> Classification</th>
        <th> Author</th>
        <th> Duration</th>
        <th> Total Score</th>
    </tr>

    <c:forEach items="${examList}" var="exam">
        <tr>
            <td>${exam.examTitle}</td>
            <td>${exam.examDescription}</td>
            <td>${exam.examClassification}</td>
            <td>${exam.teacher.name} ${exam.teacher.family}</td>
            <td>${exam.duration} (min)</td>
            <td>${exam.examMaxScore} (min)</td>
        </tr>
    </c:forEach>
</table>

<div align="center">

    <form:form modelAttribute="exam" action="examStartProcess" method="GET">

        <div align="center">
            <br><br><h3>Select Exam</h3>
            <p>${user.name}, select the Exam Title to start the exams </p>
        </div>

        <table align="center">

            <tbody>
            <tr>
                <td>
                    <form:select path="examTitle" style="width:200px">
                        <form:option value="NONE" label="Select"/>
                        <form:options  items="${examTitles}" />
                    </form:select>
                </td>
            </tr>
            </tbody>
        </table>
        <table align="center">
            <tr>
                <form:button name="submit">Select</form:button>
            </tr>
        </table>
    </form:form>
    <br><br><br>
</div>

<p align="center"><a href="/studentPanel">Student Panel</a></p>
<p align="center"><a href="/">Home</a></p>

</body>
</html>