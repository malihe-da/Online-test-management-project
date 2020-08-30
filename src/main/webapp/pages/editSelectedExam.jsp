<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Exam</title>
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

    <h4 style="color: green"> you can edit exam here and add some questions from Question
        Bank or define a new question.</h4><br><br><br>
</div>


<div align="center">
    <h3 style="color: crimson">The selected Exam ${exam.examTitle} has the following question until now:</h3>

</div>

<form:form modelAttribute="exam" method="GET">
    <div align="center">
        <table>

            <tr>
                <th>Question Type</th>
                <th>Question Title</th>
                <th>Question Description</th>
                <th>Question Face</th>
            </tr>

            <c:forEach items="${exam.questions}" var="question">
                <tr>
                    <td>${question.type}</td>
                    <td>${question.questionTitle}</td>
                    <td>${question.questionDescription}</td>
                    <td>${question.questionFace}</td>
                </tr>
            </c:forEach>

        </table>

    </div>
    <br><br><br><br><br><br>
</form:form>


<div align="center">

    <label>You can select a question from question bank here:</label>

    <form:form modelAttribute="bankQuestions" action="addSelectedQuestionToExam" method="GET">

        <table>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Show Bank
                    Question
                    <thead>
                    <tr>
                        <td>Question Title</td>
                        <td>Question Type</td>
                        <td>Question Description</td>
                        <td>Question Face</td>
                        <td>Choose</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>

                            <span class="caret"></span>
                </button>
                <c:forEach items="${bankQuestions}" var="question">
                    <ul class="dropdown-menu">
                        <li><a href="#">${question.questionTitle}</a></li>
                        <li><a href="#">${question.type}</a></li>
                        <li><a href="#">${question.questionDescription}</a></li>
                        <li><a href="#">${question.questionFace}</a></li>
                        <li><a href="#">
                            <input type="hidden" name="questionFromBank" value="${question.id}">
                            <input type="hidden" name="selectedExamId" value="${exam.id}">
                            <form:button name="submit">Add</form:button>
                        </a></li>
                    </ul>
                </c:forEach>
                </td>
                </tr>
                </tbody>
            </div>
        </table>
    </form:form>
    <br><br><br>
</div>


<form:form modelAttribute="question" action="create-newQuestion" method="GET">
    <div align="center">
        <h3 style="color:darkgreen ">Create your question here:</h3><br><br>
    </div>

    <div class="container" class="container" position="flex">
        <div class="child" position="absolute"></div>
        <fieldset>
            <div class="child" position="absolute">

                <table align="center">
                    <tr>
                        <td>
                            <form:input type="hidden" path="examId" name="selectedExamId" value="${exam.id}"/>
                            <form:label path="questionTitle">Question Title</form:label>
                        </td>
                        <td>
                            <form:input path="questionTitle" name="questionTitle" required="required"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="type">Question Type</form:label>
                        </td>
                        <td>
                            <form:radiobutton path="type" name="type" value="descriptive"/>descriptive<br>

                            <form:radiobutton path="type" name="type" value="multipleChoice"/>multipleChoice
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="questionDescription">Question Description</form:label>
                        </td>
                        <td>
                            <form:input path="questionDescription" name="questionDescription" required="required"/>
                        </td>
                        <form:input type="hidden" path="authorId" name="authorId" value="${user.id}"/>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="questionFace">Question Face</form:label>
                        </td>
                        <td>
                            <form:textarea path="questionFace" name="questionFace" rows="5" cols="30"
                                           required="required"/>
                        </td>
                    </tr>
                    <tr>

                        <td>
                            <form:label path="keyAnswer">Key Answer</form:label>
                        </td>
                        <td>
                            <form:input path="keyAnswer" name="keyAnswer" required="required"/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <form:label path="questionBankAdded">Do you want to add it to Question Bank</form:label>
                        </td>
                        <td>
                            <form:radiobutton path="questionBankAdded" name="questionBankAdded" value="yes"/>yes<br>

                            <form:radiobutton path="questionBankAdded" name="questionBankAdded" value="no"/>no
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="reset" value="Clear"/><br>

                            <form:button name="submit">Submit</form:button>
                        </td>
                    </tr>
                </table>

            </div>
        </fieldset>
        <fieldset>
            <div class="child" position="absolute" align="center">

                <div id="dynamicCheck">
                    <input type="hidden" value="0" id="total_chq">
                    <input type="button" value="Create Element" onclick="createNewElement();"/>
                </div>

                <div id="newElementId">New inputbox goes here:</div>

            </div>
        </fieldset>

    </div>
</form:form>
<%--
<form:form action="endOfQuestionPlanning" method="GET">
    <form:button name="submit">End of Exam</form:button>
</form:form>--%>
<div>
    <br><br><br>
    <p align="center"><a href="/teacherPanel">Teacher Panel</a></p>
    <p align="center"><a href="/">Home</a></p>

</div>


<script type="text/JavaScript">

    function createNewElement() {
        var counter= document.getElementById("total_chq").value;


        var txtNewInputBox = document.createElement('div');

        txtNewInputBox.innerHTML = "<input type='text' id='newInputBox' path='answerOptions[" + counter + "]' name='answerOptions[" + counter + "]'>";

        counter++;
        document.getElementById("total_chq").value= counter;
        document.getElementById("newElementId").appendChild(txtNewInputBox);
    }

    /*    function removePreviousElement() {

            var txtNewInputBox = document.createElement('div');


            txtNewInputBox.innerHTML = "<input type='text' id='newInputBox' path=\"answerOptions[i]\" name=\"answerOptions[i]\">";
            i--;

            document.getElementById("newElementId").appendChild(txtNewInputBox);
        }*/

    /*
        function add() {
            var new_chq_no = parseInt($('#total_chq').val()) + 1;
            var new_input = "<input type='text' id='new_" + new_chq_no + "'>";
            $('#new_chq').append(new_input);
            $('#total_chq').val(new_chq_no)
        }*/

    /* function remove() {
         var last_chq_no = $('#total_chq').val();
         if (last_chq_no > 1) {
             $('#new_' + last_chq_no).remove();
             $('#total_chq').val(last_chq_no - 1);
         }
     }*/
</script>
</body>
</html>