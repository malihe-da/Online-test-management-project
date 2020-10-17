<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Select Exam To Determine Status</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet" />

</head>

<body>
<div>
    <h2 style="color: darkblue">${user.name} ${user.family} panel!</h2>
</div>
<div align="center">

</div>

<div align="center">
    <h3 style="color:darkgreen">Exam List</h3>
</div>


<form:form modelAttribute="examList" method="GET">

    <div id="states">
        <table align="center">
            <tr>
                <th> Number</th>
                <th> Title</th>
                <th> Classification</th>
                <th> Description</th>
                <th> Status</th>
                <th> Start At</th>
                <th> End At</th>
            </tr>

            <c:forEach items="${examList}" var="exam">
                <tr>
                    <td align="center">${exam.id}</td>
                    <td align="center">${exam.examTitle}</td>
                    <td align="center">${exam.examClassification}</td>
                    <td align="center">${exam.examDescription}</td>
                    <td align="center">${exam.status.text}</td>
                    <td align="center">${exam.startDate}</td>
                    <td align="center">${exam.endDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <br><br><br>
    <h3 align="center">
        <label style="color: crimson"> ${message} </label>
    </h3>
</form:form>

<div class="container" class="container" position="flex">
    <div class="child" position="absolute"></div>
    <div class="child" position="absolute">

        <form name="examId" action="determine_status" method="GET">
            <fieldset>
                <div align="center">
                    <h3 style="color:darkblue ">Enter the exam number that you want to change its status</h3>
                </div>
                <table align="center">
                    <tr>
                        <td>
                            <label path="examId">Exam Number:</label>
                            <input type="number" name="examId"/>
                        </td>
                        <td>
                            <input type=submit value=Edit>
                        </td>
                    </tr>

                    <tr></tr>

                </table>
            </fieldset>
        </form>
    </div>

    <div class="child" position="absolute">
        <form name="examId" action="get_exam_report" method="GET">
            <fieldset>
                <div align="center">
                    <h3 style="color:darkblue ">Enter the test number whose report you want to view</h3>
                </div>
                <table align="center">
                    <tr>
                        <td>
                            <label path="examId">Exam Number:</label>
                            <input type="number" name="examId"/>
                        </td>
                        <td>
                            <input type=submit value="Report">
                        </td>
                    </tr>

                    <tr></tr>

                </table>
            </fieldset>
        </form>
    </div>
</div>

<p align="center"><a href="<c:url value="/teacherPanel"/>">Teacher Panel</a></p>
<p align="center"><a href="<c:url value="/"/>">Home</a></p>

</body>
</html>