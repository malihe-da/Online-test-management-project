<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>CourseSearch</title>
    <style type="text/css">
        body{
            background-color: lightsteelblue;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<form:form modelAttribute="course" action="courseSearchProcess" method="GET">
    <table align="center">
        <tr>
            <td>
                <form:input path="id" name="courseId" placeHolder="courseId"/>
            </td>
            <td>
                <form:input path="courseTitle" name="courseTitle" placeHolder="courseTitle"/>
            </td>
            <td>
                <form:input path="courseClassification" name="courseClassification" placeHolder="courseClassification"/>
            </td>
            <td>
                <form:button name="search">Search</form:button>
            </td>
        </tr>

        <tr>
        </tr>
    </table>
</form:form>

<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>