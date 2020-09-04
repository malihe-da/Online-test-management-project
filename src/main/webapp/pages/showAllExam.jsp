<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Fill Course</title>
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
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script TYPE="text/javascript">
    $('#classificationByCourse').on('change', function () {
        var data = {
            'courseClassification' : $('#courseClassification').val();
        }

        $.ajax({
            url: 'http://localhost:8082/allCourseExam/examClassification',
            method: 'GET',
            ContentType: 'json',
            contentType : 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(data),

            success: function (response) {
                var options = '';
                if (response != null) {
                    $(response).each(function (index, value) {
                        options = value;
                    });
                    $('#examListByCourseClass').load("examList", options);
                }
                $('#loading').html('');
            }
        });
    });
</script>--%>

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

<form:form modelAttribute="exam" action="showSelectedCourseExam" method="GET">
    <div align="center">
        <label for="choice"><p style="color: mediumvioletred">First select the course to see its exam:</p></label>

        <form:select path="examClassification" name="choice" id="choice" style="width:100px">
            <form:option value="NONE" label="Select"/>
            <form:options name="courseClassification"  id="courseClassification" items="${courseList}"/>
        </form:select>

        <input type=submit value=Select>
    </div>
</form:form>

    <form:form modelAttribute="examList" id="examListByCourseClass" method="GET">
    <div align="center">
        <h3 style="color:darkgreen ">You can edit your exam only.</h3>
    </div>

    <table align="center">
        <tr>
            <th>Exam Id</th>
            <th>Exam Title</th>
            <th>Exam Classification</th>
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
                <td>${exam.examClassification}</td>
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
                    <input type="number" name="examId"/>
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