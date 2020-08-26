<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Course List</title>
    <style type="text/css">
        body{
            background-color: aquamarine;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<table align="center">

    <tr>
        <th>Course Id</th>
        <th>Course Title</th>
        <th>Course Classification</th>
        <th>Course Members</th>
    </tr>

    <c:forEach items="${courseList}" var="course"  >
        <tr>
            <td>${course.id}</td>
            <td>${course.courseTitle}</td>
            <td>${course.courseClassification}</td>
            <td> <c:forEach items="${course.users}" var="user"  >
                    ${user.name}
                    ${user.family}
            </c:forEach>
        </td>
        </tr>
    </c:forEach>


</table>

<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>
