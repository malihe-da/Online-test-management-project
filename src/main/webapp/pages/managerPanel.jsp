<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>ManagerPanel</title>
    <style>
        body{
            background-color: gold;
        }

    </style>
</head>
<body>
<table align="center">
    <tr>
    </tr>
    <tr>
    </tr>
    <td><div align="center">
        <p>${manager.name}, welcome to manager panel</p>
    </div></td>
    <tr>
    </tr>
    <tr>
        <td><a href="/show_all_courses">Show All Courses"</a>
        </td>
    </tr>
    <tr>
        <td><a href="/show_all_student">Show All Students</a>
        </td>
    </tr>
    <tr>
        <td><a href="/show_all_teacher">Show All Teachers</a>
        </td>
    </tr>
    <tr>
        <td><a href="/search_in_courses">Search Course</a>
        </td>
    </tr>
    <tr>
        <td><a href="/search_in_users">Search User</a>
        </td>
    </tr>
    <tr>
        <td><a href="/editUser">Edit User</a>
        </td>
    </tr>
    <tr>
        <td><a href="/all-user-in-course">All Users in a Course</a>
        </td>
    </tr>
    <tr>
        <td><a href="/new-course">New Course</a>
        </td>
    </tr>
    <tr>
        <td><a href="/delete-course">Delete Course</a>
        </td>
    </tr>
    <tr>
        <td><a href="/fill-course">Fill Course By Users</a>
        </td>
    </tr>
    <tr>
        <td><a href="/">Home</a>
        </td>
    </tr>
</table>
</body>
</html>