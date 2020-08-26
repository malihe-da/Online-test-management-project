<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration</title>
    <style>
        body{
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
<form:form modelAttribute="manager" action="managerLoginProcess" method="GET">
    <table align="center">
        <tr></tr>
        <tr></tr>
        <tr>
            <td>
                <form:label path="emailAddress">Email Address</form:label>
            </td>
            <td>
                <form:input type="email" path="emailAddress" name="emailAddress"  size="30" required="required"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password</form:label>
            </td>
            <td>
                <form:input path="password" name="password" required="required" />
            </td>
        </tr>
        <tr>
        </tr>

        <tr>
            <td></td>
            <td>
                <form:button name="submit">Submit</form:button>
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