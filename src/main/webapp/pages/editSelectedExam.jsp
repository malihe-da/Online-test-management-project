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
            background-color: lavender;
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

    <h4 style="color: green"> you can edit exam here by adding some questions from Question
        Bank or define a new question.</h4><br><br><br>
</div>


<div align="center">
    <h3 style="color: crimson">The selected Exam ${exam.examTitle} has the following question until now:</h3>

</div>

<form:form modelAttribute="exam" action="showBankQuestion" method="GET">
    <div align="center">
        <table>

            <tr>
                <th>Question Type</th>
                <th>Question Title</th>
                <th>Question Description</th>
                <th>Question</th>
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

        <br><br><br><br><br><br>
        <form:input type="hidden" path="id" name="examId" value="${exam.id}"/>
        <label>You can select a question from question bank here:</label><br><br>
        <input type=submit value="Show Bank Question"><br><br>
    </div>

</form:form>


<div align="center">

    <form:form modelAttribute="question" action="addSelectedQuestionToExam" method="GET">

        <table>
            <div class="dropdown">

                <thead>
                <tr>
                    <th>Question Face</th>
                </tr>
                </thead>
                <tbody>
                <tr>

                    <td>

                        <input type="hidden" name="selectedExamId" value="${exam.id}">


                        <form:select path="questionFace" style="width:200px">
                            <form:option value="NONE" label="Select"/>
                            <form:options items="${bankQuestions}"/>
                        </form:select>
                    </td>
                </tr>
                </tbody>
            </div>
        </table>
        <table align="center">

            <tr>
                <form:button name="submit">Select</form:button>
            </tr>
        </table>
    </form:form>
    <br><br><br>
</div>


<div align="center">
    <h2>
        <br><br><label style="color: darkblue"> ${message} </label><br><br>
    </h2>

</div>

<form:form modelAttribute="question" action="create-newQuestion" method="GET">
    <div align="center">
        <h3 style="color:crimson ">Create your question here:</h3><br><br>
    </div>

    <div class="container" class="container" position="flex">
        <div class="child" position="absolute"></div>
        <fieldset>
            <div class="child" position="absolute">

                <table align="center">
                    <tr>
                        <td>
                            <form:input type="hidden" path="examId" name="selectedExamId" value="${exam.id}"/>
                            <form:input type="hidden" path="questionClassification" value="${exam.examClassification}"/>
                            <form:label path="questionTitle">Question Title</form:label>
                        </td>
                        <td>
                            <form:input path="questionTitle" name="questionTitle" maxlength="10"
                                        required="required"/><br>
                            <input value="(Limit entering up to 10 characters)">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="type">Question Type</form:label>
                        </td>
                        <td>
                            <form:radiobutton path="type" name="type" value="descriptive" required="required"/>descriptive<br>

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
                            <form:label path="questionFace">Question</form:label>
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
                            <form:label path="questionBankAdded">Add to Question Bank?</form:label>
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

                <div id="newElementId">New input box goes here:</div>

            </div>
        </fieldset>

    </div>
</form:form>

<form:form modelAttribute="exam" action="scoreDetermine" method="GET">
    <div align="center">
        <br><br><br><br><br><br>
        <h3 style="color:darkorchid">If the test questions are finished, please specify the score of each question</h3>
        <input type="hidden" name="selectedExamId" value="${exam.id}">
        <form:button name="submit">Determine Score</form:button>
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
        var counter = document.getElementById("total_chq").value;
        var boxNum = counter;
        boxNum++;

        var txtNewInputBox = document.createElement('div');

        txtNewInputBox.innerHTML = boxNum + ")" + "<input type='text' id='newInputBox' path='answerOptions[" + counter + "]' name='answerOptions[" + counter + "]'>";

        counter++;
        document.getElementById("total_chq").value = counter;
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