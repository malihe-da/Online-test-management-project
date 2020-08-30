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


<div>
    <h2 style="color: darkblue">${user.name} ${user.family} panel!</h2>
</div>
<div align="center">
    <h3>
    <label style="color: crimson"> ${message} </label>
    </h3>
</div>

<div align="center">
    <h3 style="color:darkgreen">${user.name} ${user.family}, you can define an exam here!</h3>
</div>

<div align="center">
        <label >First select the course to see its exam:</label>
        <form:form modelAttribute="course" action="showSelectedCourseExam" method="GET">
    <select name="course"  >
        <c:forEach items="${courseList}" var="course" >
        <option value="${course.courseClassification}">${course.courseClassification}</option>
        </c:forEach>
    </select>

</div>
</form:form>
<form:form modelAttribute="examList"  method="GET">
    <div align="center">
        <h3 style="color:darkgreen ">You can edit your exam only.</h3>
    </div>

    <table align="center">
        <tr>
            <th>Exam Id</th>
            <th>Exam Title</th>
            <th>Exam Description</th>
            <th>Exam Author Name</th>
            <th>Exam Author Family</th>
            <th>Exam Start At</th>
            <th>Exam End At</th>
        </tr>

        <c:forEach items="${examList}" var="exam">
        <tr>
            <td>${exam.id}</td>
            <td>${exam.examTitle}</td>
            <td>${exam.examDescription}</td>
            <td>${exam.examAuthorName}</td>
            <td>${exam.examAuthorFamily}</td>
            <td>${exam.examStart}</td>
            <td>${exam.examEnd}</td>
        </tr>
            </c:forEach>
    </table>
</form:form>
    <div align="center">
        <h3 style="color:darkgreen ">Enter the exam you want to edit</h3>
    </div>

<form name="examId" action="editExam" method="GET">
    <table align="center">
        <tr>
            <td>
                <label path="examId">Exam Id</label>
                <input  type="number" name="examId"/>
            </td>
            <td>
                <input type=submit value=Edit>
            </td>
        </tr>

        <tr></tr>

    </table>
</form>


<p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
<p align="center"><a href="/">Home</a></p>
</body>
</html>