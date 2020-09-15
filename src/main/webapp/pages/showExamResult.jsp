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
    <h2 style="color: darkblue">${user.name} ${user.family} </h2>

    <h4 style="color: green"> The score of your exam ${examTitle}:</h4><br><br><br>
</div>

<div align="center">
    <h2>
        <br><br><label style="color: darkblue"> ${message} </label><br><br>
    </h2>

</div>
<form:form  method="GET">

    <div align="center">
        <table>

            <tr>
                <th>Exam Title</th>
                <th>Max Score</th>
                <th>Correct Answers</th>
                <th>Your Score</th>
            </tr>

                <tr>
                    <td>${examTitle}</td>
                    <td>${maxScore}</td>
                    <td>${correctCounter}</td>
                    <td>${totalScore}</td>

                </tr>


        </table>

    </div>


</form:form>


<div>
    <br><br><br>
    <p align="center"><a href="/studentPanel">Student Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


</body>
</html>