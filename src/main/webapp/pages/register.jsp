<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration</title>
    <style>
        body {
            background-color: lightskyblue;
        }

    </style>
</head>
<body>
<form:form modelAttribute="user" action="registerProcess" method="GET">
    <table align="center">
        <tr>
            <td>
                <form:label path="name"><b>Name</form:label>
            </td>
            <td>
                <form:input path="name" name="name" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="family"><b>Family</form:label>
            </td>
            <td>
                <form:input path="family" name="family" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password"><b>Password</form:label>
            </td>
            <td>
                <form:input type="password" path="password" name="password" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <label><b>Repeat Password</b></label>
            </td>
            <td>
                <input type="password" placeholder="Repeat Password" path="pswRepeat" name="pswRepeat" required><br>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userRole"><b>Your Role</form:label>
            </td>
            <td>
                <form:radiobutton path="userRole" name="userRole" value="student"/> Student
            </td>
            <td>
                <form:radiobutton path="userRole" name="userRole" value="teacher"/> Teacher
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="emailAddress">Email Address</form:label>
            </td>
            <td>
                <form:input type="email" path="emailAddress" name="emailAddress" size="30" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="status">Status</form:label>
            </td>
            <td>
                <option value="unverified" path="status" name="status">Unverified</option>
                <form:input type="hidden" path="status" name="status" value="unverified"/>
            </td>
        </tr>

        <tr>
            <td></td>
            <td> <input type="reset" value="Clear"/></td>
            <td></td>
            <td>
                <form:button name="register">Register</form:button>
            </td>
        </tr>

        <tr></tr>
        <tr>
            <td></td>
            <td><a href="/">Home</a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>