<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Define New Exam</title>
    <style>
        body {
            background-color: lightpink;
        }

    </style>
</head>
<body>

<div >
    <h3 style="color: crimson">${user.name} ${user.family}, you can define an exam here!</h3>
</div>
<div align="center">
    <h2>
        <br><br><label style="color: darkblue"> ${message} </label><br><br>
    </h2>

</div>
<div align="center">
    <h3 style="color:darkgreen ">To define a new exam please fill the following fields</h3>
</div>

<form:form modelAttribute="exam" action="add-newExam" method="GET">
    <table align="center">
        <tr>
            <td>
                <form:label path="examTitle">Exam Title</form:label>
            </td>
            <td>
                <form:input path="examTitle" name="examTitle" required="required"/>
            </td>
        </tr> <tr>
            <td>
                <form:label path="examCourseTitle">Exam's Course Title</form:label>
            </td>
        <td>
                <form:select path="examCourseTitle">
                    <form:option value = "NONE" label = "Select"/>
                    <form:options items="${courseTitle}"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="examDescription">Exam Description</form:label>
            </td>
            <td>
                <form:input   path="examDescription" name="examDescription"  required="required"/>
            </td>
        </tr>
        <tr>
            <td>
            <label path="examStart">start exam (date and time)</label>
            </td>
            <td>
            <form:input type="datetime-local" path="startDate" name="examStart" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
            <label path="examEnd">start exam (date and time)</label>
            </td>
            <td>
            <form:input type="datetime-local" path="endDate" name="examEnd" required="required"/>
            </td>
        </tr>
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


<p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
<p align="center"><a href="/">Home</a></p>
</body>
</html>