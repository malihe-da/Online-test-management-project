<%@ page import="static ir.maktab.finalproject.model.entity.AnswerSheet_.exam" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Determine Exam Score</title>
    <link href="<c:url value="/resources/theme/css/style1.css"/>" rel="stylesheet"/>
    <style>
        body {
            background-color: lightskyblue;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>


<div>
    <h2 style="color: darkblue">${user.name} ${user.family} panel!</h2>

    <h4 style="color: green"> you can determine the score of each question here.</h4><br><br><br>
</div>


<div align="center">
    <h3 style="color: crimson">The selected Exam ${exam.examTitle} has the following question until now:</h3>

</div>

<form:form modelAttribute="questionList" action="examScoreComplete" method="GET">
    <div align="center">
        <table>

            <tr>

                <th>Title</th>
                <th>Type</th>
                <th>Description</th>
                <th scope="col">Question</th>
                <th>Score</th>
            </tr>
            <input type="hidden" path="examId" name="examId" value="${examId}"/>

            <c:forEach items="${questionList}" var="question" varStatus="status">
                <tr>
                    <td>${question.questionTitle}</td>
                    <td>${question.type}</td>
                    <td>${question.questionDescription}</td>
                    <td>
                        <input value="${question.questionFace}" rows="5" cols="30"></td>
                    <td>
                            <%--<form:label path="questionScoresMap['${question.key}']"></form:label>--%>
                        <label >
                            <input  name="value"
                                   required="required"/>
                        </label>


                    </td>
                </tr>
            </c:forEach>

        </table>
        <%--<form:input type="hidden" path="questionScoresMap" value="exam.questionScoresMap"/>--%>
    </div>

    <%--<fieldset>
        <div class="child" position="absolute" align="center">

            <div id="dynamicCheck">
                <input type="hidden" value="0" id="total_chq">
                <input type="hidden" value="questionSize" id="exam_end">
                <input type="button" value="Create Element" onclick="createNewElement();"/>
            </div>

            <div id="newElementId">Question Score</div>

        </div>
    </fieldset>--%>
    <div align="center">
        <br><br><br><br><br><br>
        <h3 style="color:darkorchid">End of scoring</h3>
        <form:button name="submit">The End</form:button>
        <br><br><br>
    </div>

</form:form>


<div>
    <br><br><br>
    <p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


<script type="text/JavaScript">

    function createNewElement() {
        var qId = document.getElementById("total_chq").value;
        var max = document.getElementById("exam_end").value;

        if (counter < max) {
            txtNewInputBox = document.createElement('div');
            txtNewInputBox.innerHTML = "<br><input  id='newInputBox' path='questionScores[" + counter + "]' name='questionScores[" + counter + "]' placeholder='questionScores[" + counter + "]'>";

            counter++;
            document.getElementById("total_chq").value = counter;
            document.getElementById("newElementId").appendChild(txtNewInputBox);
        } else {
            document.getElementById("newElementId").innerHTML = "Scoring questions are over";
        }
    }
</script>
</body>
</html>