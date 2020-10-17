<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Course List</title>
    <style type="text/css">
        .container {
            display: flex;
        }

        nav {
            width: 50vh;
            height: 10vh;
        }
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
<head>
   <h2 style="color: darkgreen" align="center"> Course List:</h2>
</head>
<div class="container">
    <nav></nav>
</div>
<table align="center">

    <tr>
        <th>Course Title</th>
        <th>Course Classification</th>
        <th>Course Members</th>
    </tr>

    <c:forEach items="${courseList}" var="course"  >
        <tr>
            <td align="center">${course.courseTitle}</td>
            <td align="center">${course.courseClassification}</td>
            <td align="center"> <c:forEach items="${course.users}" var="user"  >
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
