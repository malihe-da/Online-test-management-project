<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>UserSearch</title>

    <style>
        body {
            background-color: lightsteelblue;
        }
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<form:form modelAttribute="user" action="userSearchProcess" method="GET">
    <table align="center">

        <tr>
            <th>Name</th>
            <th>Family</th>
            <th>User Role</th>
            <th>Email Address</th>
            <th>Status</th>
        </tr>

        <tr>
            <td>
                <form:input path="name" name="name" placeHolder="Name"/>
            </td>
            <td>
                <form:input path="family" name="family" placeHolder="Family"/>
            </td>
            <td>
                <form:input path="userRole" name="userRole" placeHolder="userRole"/>
            </td>
            <td>
                <form:input path="emailAddress" name="emailAddress" placeHolder="emailAddress"/>
            </td>
            <td>
                <form:input path="status" name="status" placeHolder="status"/>
            </td>
            <td>
                <form:button name="search">Search</form:button>
            </td>

        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.family}</td>
                <td>${user.userRole}</td>
                <td>${user.emailAddress}</td>
                <td>${user.status}</td>
            </tr>
        </c:forEach>

    </table>
</form:form>


<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>
</body>
</html>