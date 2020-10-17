<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>UserList</title>
    <style type="text/css">
        .container {
            display: flex;
        }

        nav {
            width: 50vh;
            height: 10vh;
        }
        body {
            background-color: aquamarine;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<div class="container">
    <nav></nav>
</div>
<div align="center">
    <h2 style="color: darkblue"></h2>
</div>

<table align="center">

    <tr>
        <th>Name</th>
        <th>Family</th>
        <th>Role</th>
        <th>Email Address</th>
        <th>Manager Verified</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td align="center">${user.name}</td>
            <td align="center">${user.family}</td>
            <td align="center">${user.userRole}</td>
            <td align="center">${user.emailAddress}</td>
            <td align="center">${user.status}</td>
        </tr>
    </c:forEach>
<%--
</table>
<div></div>

<table id="ajaxTest">

</table>
--%>

<p align="center"><a href="/editUser">Edit User</a></p>
<p align="center"><a href="/">Home</a></p>
<p align="center"><a href="/managerPanel">Manager Panel</a></p>


</body>
<script>
    window.onload = function () {
        loadDoc("${pageNumber}")
    }

    function loadDoc() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var data = JSON.parse(this.responseText);
                showData(data);
                console.log(data);
            }
        };
        xhttp.open("GET", "http://localhost:8082/rest/showStudents", true);
        xhttp.send();
    }

    function showData(data) {
        var table = '<tr><th>First Name</th><th>Last Name</th></tr>'
        data.map(value => table += '<tr><td>' + value.name + '</td><td>' + value.family + '</td></tr>'
        )
        document.getElementById("ajaxTest").innerHTML = table;
    }
</script>
</html>
