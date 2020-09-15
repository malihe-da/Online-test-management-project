<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Determine Exam Score</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
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


<div>
    <h2 style="color: darkblue">${user.name} ${user.family} panel!</h2>


</div>


<div align="center">
    <h4 style="color: green"> The score of each questions is as follows.</h4><br><br><br>
    <h3 style="color: crimson">The selected Exam ${exam.examTitle} has the following question until now:</h3>

</div>

<form:form modelAttribute="exam"  method="GET">

    <div align="center">
        <table>

            <tr>
                <th>Question Title</th>
                <th>Question Type</th>
                <th>Question Description</th>
                <th>Question Face</th>
                <th>Question Score</th>
            </tr>

            <c:forEach items="${exam.questions}" var="question">
                <tr>
                    <td>${question.questionTitle}</td>
                    <td>${question.type}</td>
                    <td>${question.questionDescription}</td>
                    <td>${question.questionFace}</td>
                    <td>${question.questionScore}</td>
                </tr>
            </c:forEach>

        </table>

    </div>

    <div align="center">
        <table>

            <tr>
                <th>Exam max Score</th>
            </tr>
            <tr>
                   <td> ${exam.examMaxScore}</td>
            </tr>
        </table>
    </div>

</form:form>


<div>
    <br><br><br>
    <p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


</body>
</html>