<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
    <style>
        .container {
            display: flex;
        }

        nav {
            width: 50vh;
            height: 100vh;
        }

        header {
            height: 10%;
        }

        aside {
            display: flex;
            width: 20%;
            height: 100%;
        }

        footer {
            height: 20%;
        }
        .artDiv{
            width: 100%;
        }
        .center{
            display: flex;
            height: 80%;
        }
    </style>

</head>
<body>

<div class="container">
    <nav></nav>
    <main>
        <header>
            <a href="/">Home</a>
        </header>
        <%--<aside></aside>--%>
        <div class="center">
            <div class="artDiv">
                <article>

                    <div align="center">
                    <form:form modelAttribute="user" action="loginProcess" method="GET">
                        <table align="center">
                            <tr></tr>
                            <tr></tr>
                            <tr>
                                <td>
                                    <form:label path="emailAddress">Email Address</form:label>
                                </td>
                                <td>
                                    <form:input type="email" path="emailAddress" name="emailAddress" size="30"
                                                required="required"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="password">Password</form:label>
                                </td>
                                <td>
                                    <form:input path="password" name="password" required="required"/>
                                </td>
                            </tr>

                        </table>
                        <div align="center">
                        <form:button name="submit">Submit</form:button>
                    </div>
                    </form:form>
                    </div>
                    <footer></footer>
                </article>
            </div>
        </div>
    </main>
</div>
</body>
</html>