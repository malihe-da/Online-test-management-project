<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Maktab Home</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
    <link href="<c:url value="/resources/theme/css/test.js"/>" rel="stylesheet"/>
</head>
<body>

<div align="center">
    <br><br>
    <h1>Welcome to Maktab online exam</h1><br><br>
</div>

<div class="container" class="container" position="flex">

    <div class="child" position="absolute"></div>
    <div class="child" position="absolute">
        <form action="login" method="GET">
            <fieldset>
                <h3 style="color: darkgreen"> For login click the following button</h3>
                <button class="button" type="submit">Sign in</button>
            </fieldset>
        </form>
    </div>

    <div class="child" position="absolute">
        <form action="managerLogin" method="GET">
            <fieldset>
                <h3 style="color: darkblue"> Manager login</h3>
                <button class="button" type="submit">Manager</button>
            </fieldset>
        </form>
    </div>

</div>

<div class="container" class="container" position="flex">
    <div class="child" position="absolute"></div>
        <div class="child" position="absolute">
            <form action="register" method="GET">
                <fieldset>
                    <h3 style="color: darkred"> For Register click the following button</h3>
                    <button class="button" type="submit">New User</button>
                </fieldset>
            </form>
        </div>
    </div>



</body>
</html>