<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Correction</title>
    <style>
        body {
            background-color: gainsboro;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>


<form:form modelAttribute="answerSheet" action="correctNextQuestion" method="GET">
    <div align="center">
        <h2>Question:</h2>
        <h3>${answerSheet.questionsCounter}) ${question.questionFace} (Question Score: ${questionScore})</h3><br>
        <p style="color: seagreen"> key Answer: ${question.keyAnswer}</p><br>
        <input type="hidden" name="questionId" value="${question.id}">
        <input type="hidden" name="answerSheetId" value="${answerSheet.id}">

    </div>
    <div align="center">
        <h3>Student Answer:</h3>
        <p style="font-size: large; color: darkblue" > ${answerSheet.userAnswerSheet.get(question)}</p>
        <input type="hidden" path="questionsCounter" name="questionsCounter" value="${answerSheet.questionsCounter}">
    </div>

    <div align="center">

        <form:label path="grade">Student Grade:</form:label>
        <form:input   path="grade" name="questionGrade" size="7" required="required"/> From ${questionScore}

            <h2>
               <br><label style="color: crimson"> ${message} </label>
            </h2>



    </div>

    <br><br>
    <div align="center">
        <br><br>
        <form:button name="submit">Next</form:button>
    </div>

</form:form>

<form:form modelAttribute="answerSheet" action="prevQuestionCorrection" method="GET">

    <input type="hidden" name="questionId" value="${question.id}">
    <input type="hidden" name="answerSheetId" value="${answerSheet.id}">
    <input type="hidden" path="questionsCounter" name="questionsCounter" value="${answerSheet.questionsCounter}">
    <input type="hidden" name="remainTime" value=""/>

    <div align="center">
        <br><br>
        <form:button name="submit">Previous</form:button>
    </div>

</form:form>



</body>
</html>