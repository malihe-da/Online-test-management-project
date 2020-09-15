<%@ page import="ir.maktab.finalproject.model.enums.Status" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Determine Status</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
    <style>
        body {
            background-color: lavender;
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
    <h3 style="color: crimson">Determine status of Exam ${exam.examTitle}:</h3>

</div>


<div align="center">
    <h2>
        <br><br><label style="color: darkblue"> ${message} </label><br><br>
    </h2>

</div>

<form:form modelAttribute="exam" action="applyStatusChange" method="GET">
    <div align="center">

                <P style="color: darkblue">Exam Status</P><br><br>

    <p><form:radiobuttons path="status" items="<%= Status.values() %>"/><br></p><br>


        <br><br><br><br><br><br>
        <form:input type="hidden" path="id" name="examId" value="${exam.id}"/>
        <form:button name="submit">submit</form:button>
    </div>

</form:form>
<div>
    <br><br><br>
    <p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


</body>
</html>