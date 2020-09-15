<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Correction Result</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
    <style>
        body {
            background-color: lightcyan;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>


<div>

</div>

<div align="center">
    <h2>
        <br><br><label style="color: darkblue"> ${message} </label><br><br>
    </h2>

</div>
<form:form modelAttribute="exam" action="answerSheer-correction-process" method="GET">

    <div align="center">
        <h4 style="color: green"> Result</h4><br>
        <table>

            <tr>
                <th>Exam Title</th>
                <th>Max Score</th>
                <th>Student Name</th>
                <th>Student Score</th>
            </tr>

            <tr>
                <td>${exam.examTitle}</td>
                <td>${exam.examMaxScore}</td>
                <td>${answerSheet.user.name} ${answerSheet.user.family}</td>
                <td>${answerSheet.totalExamScore}</td>

            </tr>
        </table>
        <input type="hidden" path="examTitle" name="examTitle" value="${exam.examTitle}">
        <form:button name="submit">Next AnswerSheet</form:button>
    </div>


</form:form>


<div>
    <br><br><br>
    <p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


</body>
</html>