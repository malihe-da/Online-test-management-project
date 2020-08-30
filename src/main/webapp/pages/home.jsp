<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Maktab Home</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet" />
    <link href="<c:url value="/resources/theme/css/test.js"/>" rel="stylesheet" />
</head>
<body>

<div  align="center" >
    <br><br><h1>Welcome to Maktab online exam</h1><br><br>
</div>

<div class="container" class="container" position="flex">

    <div  class="child"  position="absolute"></div>
<div  class="child" position="absolute">
    <form action="login" method="GET">
        <fieldset>
            <p> For login click the following button</p>
            <button class="button" type="submit">Sign in</button>
        </fieldset>
    </form>
</div>

<div  class="child"  position="absolute">
    <form action="register" method="GET">
        <fieldset>
            <p> For Register click the following button</p>
            <button class="button" type="submit">New User</button>
        </fieldset>
    </form>
</div>

    <div  class="child"  position="absolute">
        <form action="managerLogin" method="GET">
            <fieldset>
                <p> Manager login</p>
                <button class="button" type="submit">Manager</button>
            </fieldset>
        </form>
    </div>
</div>



<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>