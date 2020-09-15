<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Start Exam</title>
    <style>
        body {
            background-color: gold;
        }

        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<div>
    <h3>${user.name} ${user.family}, exam page</h3>
</div>


<form:form modelAttribute="answerSheet" action="nextQuestion" method="GET">
    <div align="center">
        <h2>Question:</h2>
        <p>${answerSheet.questionsCounter}) ${question.questionFace}</p><br><br>
        <input type="hidden" name="questionId" value="${question.id}">
        <input type="hidden" name="answerSheetId" value="${answerSheet.id}">
        <input type="hidden" name="remainTime" value=""/>

    </div>
    <div align="center">
        <h2>Answer:</h2>
        <input type="hidden" path="questionsCounter" name="questionsCounter" value="${answerSheet.questionsCounter}">

        <p><form:radiobuttons path="answer" items="${answerOptions}"/></p><br>

    </div>
    <div align="center">
        <form:textarea path="answer" name="descriptiveAnswer" rows="5" cols="60"/>
    </div>
    <br><br>
    <div align="center">
        <br><br>
        <form:button name="submit">Next</form:button>
    </div>

</form:form>

<form:form modelAttribute="answerSheet" action="prevQuestion" method="GET">

    <input type="hidden" name="questionId" value="${question.id}">
    <input type="hidden" name="answerSheetId" value="${answerSheet.id}">
    <input type="hidden" path="questionsCounter" name="questionsCounter" value="${answerSheet.questionsCounter}">
    <input type="hidden" name="remainTime" value=""/>

    <div align="center">
        <br><br>
        <form:button name="submit">Previous</form:button>
    </div>

</form:form>


<form:form modelAttribute="answerSheet" action="examFinished" method="GET">
    <input type="hidden" name="questionId" value="${question.id}">
    <input type="hidden" name="answerSheetId" value="${answerSheet.id}">
    <input type="hidden" path="questionsCounter" name="questionsCounter" value="${answerSheet.questionsCounter}">
    <input type="hidden" name="remainTime" value=""/>

    <div align="center">
        <br>
        <form:button id="done" name="submit">End</form:button>
    </div>

</form:form>
<!-- Timer -->
<input type="hidden" id="examTime" value="${examTime}"/>
<div>Remaining time: <span id="tm"></span></div>

<p align="center"><a href="/">Home</a></p>



<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script th:inline="javascript">
    $(document).ready(function () {
        // Timer functions
        var countDownTimer = parseInt($('#examTime').val());

        function timerFunction() {
            var m = ((countDownTimer / 60) >> 0);
            var s = (countDownTimer % 60);
            $('#tm').text(("00" + m).slice(-2) + ':' + ("00" + s).slice(-2));
            if (countDownTimer === 0) {
                $('#done').click();
            }
            countDownTimer -= 1;

            $('input[name="remainTime"]').val(countDownTimer);
        }
        setInterval(timerFunction, 1000);
    });

</script>

</body>
</html>