<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Define New Course</title>
    <style>
        body {
            background-color: lightskyblue;
        }

        .button {
            background-color: seashell;
            border: none;
            color: black;
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
<form:form modelAttribute="course" action="newCourseProcess" method="GET">
    <table align="center">
        <tr>
            <td>
                <form:label path="courseTitle">Course Title</form:label>
            </td>
            <td>
                <form:input path="courseTitle" name="courseTitle" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="courseClassification">courseClassification</form:label>
            </td>
            <td>
                <form:input   path="courseClassification" name="courseClassification"  required="required"/>
            </td>
        </tr>
        <tr></tr>
        <tr></tr>
        <tr>
            <td></td>
            <td> <input type="reset" value="Clear"/></td>
            <td></td>
            <td>
                <form:button name="submit">Submit</form:button>
            </td>
        </tr>

        <tr></tr>

    </table>
</form:form>


<p align="center"><a href="/managerPanel">Manager Panel</a></p>
<p align="center"><a href="/">Home</a></p>
</body>
</html>