<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Start Exam</title>
    <style>
        body {
            background-color: gold;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        button {
            background-color: MidnightBlue;
            border: none;
            color: white;
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
<div>
    <h3>${user.name} ${user.family}, exam page</h3>
</div>

<div align="center">
    <h2 style="color: mediumvioletred ">This test, ${exam.examTitle}, </h2>
    <h3 style="color: mediumvioletred "> consists of ${exam.questionCount} questions
        that you must answer within ${exam.duration} minutes. Before the end of the test,
        it is possible to return to the previous questions and correct your answers.</h3>
    <h2 style="color: darkred">Good luck</h2>
</div>


<form:form modelAttribute="exam" action="examStart" method="GET">
    <div align="center">
        <br><br>
        <form:input type="hidden" path="examTitle" value="${exam.examTitle}"/>
        <label>Click to start the exam:</label><br><br>
        <form:button name="submit">Start</form:button>
    </div>
</form:form>


<p align="center"><a href="/">Home</a></p>

</body>
</html>