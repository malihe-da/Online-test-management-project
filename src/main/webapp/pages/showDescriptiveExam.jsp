<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Choose Descriptive Exam</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet" />

</head>

<body>
<div align="center">
    <h3>
        <label style="color: crimson"> ${message} </label>
    </h3>
</div>

<div align="center">
    <h3 style="color:darkgreen">List of descriptive Exam:</h3>
</div>


<form:form modelAttribute="examList" method="GET">

    <div id="states">
        <table align="center">
            <tr>
                <th> Number</th>
                <th> Title</th>
                <th> Type</th>
                <th> Classification</th>
                <th> Description</th>
                <th> Status</th>
                <th> Start At</th>
                <th> End At</th>
            </tr>

            <c:forEach items="${examList}" var="exam">
                <tr>
                    <td>${exam.id}</td>
                    <td>${exam.examTitle}</td>
                    <td>${exam.examType}</td>
                    <td>${exam.examClassification}</td>
                    <td>${exam.examDescription}</td>
                    <td>${exam.status.text}</td>
                    <td>${exam.startDate}</td>
                    <td>${exam.endDate}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <br><br><br>
</form:form>


    <div align="center">

        <form:form modelAttribute="exam" action="answerSheer-correction-process" method="GET">

            <div align="center">
                <br><br><h3>Select Exam</h3>
                <p>${user.family}, select the Exam Title to correct the exam sheets</p>
            </div>

            <table align="center">

                <tbody>
                <tr>
                    <td>
                        <form:select path="examTitle" style="width:200px">
                            <form:option value="NONE" label="Select"/>
                            <form:options  items="${examTitles}" />
                        </form:select>
                    </td>
                </tr>
                </tbody>
            </table>
            <table align="center">
                <tr>
                    <form:button name="submit">Select</form:button>
                </tr>
            </table>
        </form:form>
    </div>

</div>

<p align="center"><a href="<c:url value="/teacherPanel"/>">Teacher Panel</a></p>
<p align="center"><a href="<c:url value="/"/>">Home</a></p>

</body>
</html>