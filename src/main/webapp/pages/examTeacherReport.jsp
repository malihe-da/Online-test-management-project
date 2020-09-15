<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title> Exam Report</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
    <style>
        body {
            background-color: gainsboro;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>


<div>
    <h2 style="color: darkblue">${user.name} ${user.family} panel!</h2

</div>


<div align="center">
   <br><br><br> <p style="color: darkblue"> Exam Information</p> <br><br>
<table>
    <tr>
        <th>Exam Title</th>
        <th>Number of Student</th>
    </tr>
    <tr>
        <td>${exam.examTitle}</td>
        <td>${studentCount}</td>
    </tr>
</table>
</div>


    <div align="center">
        <br><br><br> <p style="color: darkblue"> Students AnswerSheetReport</p> <br><br>
        <table>

            <tr>
                <th>Student Name</th>
                <th>Student Family</th>
                <th>Exam Score</th>
            </tr>

            <c:forEach items="${answerSheetList}" var="answerSheet">
                <tr>
                    <td>${answerSheet.user.name}</td>
                    <td>${answerSheet.user.family}</td>
                    <td>${answerSheet.totalExamScore}</td>
                </tr>
            </c:forEach>

        </table>

    </div>



<div>
    <br><br><br>
    <p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


</body>
</html>