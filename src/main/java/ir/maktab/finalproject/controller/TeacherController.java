package ir.maktab.finalproject.controller;


import ir.maktab.finalproject.model.entity.*;
import ir.maktab.finalproject.model.enums.Type;
import ir.maktab.finalproject.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SessionAttributes("user")
@Controller
public class TeacherController {

    ExamService examService;
    UserService userService;
    QuestionService questionService;
    CourseService courseService;
    QuestionBankService questionBankService;
    AnswerSheetService answerSheetService;

    public TeacherController(UserService userService,
                             ExamService examService,
                             QuestionService questionService,
                             CourseService courseService,
                             QuestionBankService questionBankService,
                             AnswerSheetService answerSheetService) {
        this.userService = userService;
        this.questionService = questionService;
        this.examService = examService;
        this.courseService = courseService;
        this.questionBankService = questionBankService;
        this.answerSheetService = answerSheetService;
    }

    @RequestMapping(value = "/teacherPanel", method = RequestMethod.GET)
    public String getTeacherPanel(@ModelAttribute("user") User user,
                                  Model model) {
        model.addAttribute("user", user);
        return "teacherPanel";
    }

    @RequestMapping(value = "allCourseExam", method = RequestMethod.GET)
    public String selectACourseToSeeExam(@ModelAttribute("user") User user,
                                         Model model) {
        List<String> courseClassifications = courseService.getAllCoursesClassifications();

        model.addAttribute("user", user);
        model.addAttribute("exam", new Exam());
        model.addAttribute("courseList", courseClassifications);
        return "showAllExam";
    }

    @RequestMapping(value = "showSelectedCourseExam", method = RequestMethod.GET)
    public String showAllExam(@ModelAttribute("user") User user,
                              @ModelAttribute("exam") Exam exam,
                              Model model) {
        List<Exam> examList = examService.getExamsByClassification(exam.getExamClassification());
        List<String> courseClassifications = courseService.getAllCoursesClassifications();
        model.addAttribute("user", user);
        model.addAttribute("course", new Course());
        model.addAttribute("courseList", courseClassifications);
        model.addAttribute("examList", examList);
        model.addAttribute("exam", new Exam());
        return "showAllExam";
    }


    @RequestMapping(value = "new-exam", method = RequestMethod.GET)
    public String defineNewExam(@ModelAttribute("user") User user,
                                Model model) {
        List<String> courseTitle = courseService.getAllCourseTitle();
        model.addAttribute("user", user);
        model.addAttribute("exam", new Exam());
        model.addAttribute("courseTitle", courseTitle);
        return "newExam";
    }

    @RequestMapping(value = "add-newExam", method = RequestMethod.GET)
    public String saveNewExam(@ModelAttribute("user") User user,
                              @ModelAttribute("exam") Exam exam,
                              Model model) {

        exam.setTeacher(userService.getUserById(user.getId()));
        examService.saveExam(exam);
        List<String> courseTitle = courseService.getAllCourseTitle();
        model.addAttribute("message", "The test was successfully registered. " +
                "Please proceed to the exam editing portal to complete it");
        model.addAttribute("user", user);
        model.addAttribute("exam", new Exam());
        model.addAttribute("courseTitle", courseTitle);
        return "newExam";
    }

    @RequestMapping(value = "editExam", method = RequestMethod.GET)
    public String selectExamToEdit(@ModelAttribute("user") User user,
                                   HttpServletRequest httpServletRequest,
                                   Model model) {

        int id = Integer.parseInt(httpServletRequest.getParameter("examId"));
        Exam selectedExam = examService.getExamById(id);
        if (selectedExam.getTeacher().getId() == user.getId()) {
            model.addAttribute("bankQuestions", new ArrayList<>());
            model.addAttribute("user", user);
            model.addAttribute("question", new Question());
            model.addAttribute("exam", selectedExam);
            return "editSelectedExam";
        } else {
            model.addAttribute("message", "You select the other author to edit. It is not possible");
            List<String> bankQuestions = questionBankService.getClassifiedQuestionsInBank(selectedExam.getExamClassification());
            List<String> courseClassifications = courseService.getAllCoursesClassifications();
            model.addAttribute("courseList", courseClassifications);
            model.addAttribute("user", user);
            model.addAttribute("bankQuestions", bankQuestions);
            model.addAttribute("exam", new Exam());
            return "showAllExam";
        }
    }


    @RequestMapping(value = "showBankQuestion", method = RequestMethod.GET)
    public String showQuestionFromBank(@ModelAttribute("user") User user,
                                       @ModelAttribute("exam") Exam exam,
                                       Model model) {
        Exam selectedExam = examService.getExamById(exam.getId());
        List<String> bankQuestions = questionBankService.getClassifiedQuestionsInBank(selectedExam.getExamClassification());
        model.addAttribute("bankQuestions", bankQuestions);
        model.addAttribute("user", user);
        model.addAttribute("question", new Question());
        model.addAttribute("exam", selectedExam);
        return "editSelectedExam";

    }

    @RequestMapping(value = "addSelectedQuestionToExam", method = RequestMethod.GET)
    public String addQuestionFromBankToExam(@ModelAttribute("user") User user,
                                            @ModelAttribute("question") Question question,
                                            HttpServletRequest httpServletRequest,
                                            Model model) {


        int examId = Integer.parseInt(httpServletRequest.getParameter("selectedExamId"));
        Exam selectedExam = examService.getExamById(examId);
        Question selectedQuestion = questionService.getQuestionByQuestionFace(question.getQuestionFace(), selectedExam.getExamClassification());

        examService.addQuestionToExam(selectedQuestion, selectedExam);
        List<String> bankQuestions = questionBankService.getClassifiedQuestionsInBank(selectedExam.getExamClassification());
        model.addAttribute("bankQuestions", bankQuestions);
        model.addAttribute("user", user);
        model.addAttribute("question", new Question());
        model.addAttribute("exam", selectedExam);
        return "editSelectedExam";

    }

    @RequestMapping(value = "create-newQuestion", method = RequestMethod.GET)
    public String addCreatedQuestionToExam(@ModelAttribute("user") User user,
                                           @ModelAttribute("question") Question question,
                                           @ModelAttribute("exam") Exam exam,
                                           Model model) {


        Exam selectedExam = examService.getExamById(question.getExamId());
        List<Question> bankQuestions = questionBankService.getQuestionsInBank();
        model.addAttribute("bankQuestions", bankQuestions);
        model.addAttribute("user", user);
        model.addAttribute("question", new Question());
        model.addAttribute("exam", selectedExam);
        if (question.getType().equals("multipleChoice")) {
            if (!questionService.fixQuestionAnswerKey(question)) {
                model.addAttribute("message", "For multiple choice questions, you must select a key from the alphabet!");
                return "editSelectedExam";
            }
        }

        questionService.saveNewQuestion(question);
        examService.addQuestionToExam(question, selectedExam);
        if (question.getQuestionBankAdded() != null) {
            if (question.getQuestionBankAdded().equals("yes")) {
                questionBankService.addQuestionIdToBank(question);
            }
        }
        return "editSelectedExam";

    }

    @RequestMapping(value = "scoreDetermine", method = RequestMethod.GET)
    public String endOfQuestionPlanning(@ModelAttribute("user") User user,
                                        HttpServletRequest httpServletRequest,
                                        Model model, HttpSession session) {
        int examId = Integer.parseInt(httpServletRequest.getParameter("selectedExamId"));
        Exam selectedExam = examService.getExamById(examId);
        selectedExam = examService.preparedToDetermineScore(selectedExam);

        List<Question> questionList = selectedExam.getQuestions();
        model.addAttribute("user", user);
        model.addAttribute("questionList", questionList);
        model.addAttribute("examId", examId);
        model.addAttribute("exam", selectedExam);
        return "scoreDetermine";

    }

    @RequestMapping(value = "examScoreComplete", method = RequestMethod.GET)
    public String endOfQuestionScore(@ModelAttribute("user") User user,
                                     HttpServletRequest request,
                                     Model model) {
        int examId = Integer.parseInt(request.getParameter("examId"));
        Exam selectedExam = examService.getExamById(examId);
        String[] values = request.getParameterValues("value");

        selectedExam = examService.saveQuestionsScoreFromArray(selectedExam, values);
        List<Question> questionList = selectedExam.getQuestions();

        model.addAttribute("user", user);
        model.addAttribute("questionList", questionList);
        model.addAttribute("exam", selectedExam);
        return "showScores";

    }


    @RequestMapping(value = "determine_exam_status_report", method = RequestMethod.GET)
    public String determineExamStatus(@ModelAttribute("user") User user,
                                      Model model) {

        List<Exam> examList = examService.getAllExamsOfATeacher(user);
        model.addAttribute("user", user);
        model.addAttribute("examList", examList);
        return "selectExamToDetermineStatus";

    }

    @RequestMapping(value = "determine_status", method = RequestMethod.GET)
    public String determineSelectedExamStatus(@ModelAttribute("user") User user,
                                              HttpServletRequest request,
                                              Model model) {
        int id = Integer.parseInt(request.getParameter("examId"));
        Exam selectedExam = examService.getExamById(id);
        model.addAttribute("user", user);
        model.addAttribute("exam", selectedExam);
        return "determineStatus";
    }

    @RequestMapping(value = "applyStatusChange", method = RequestMethod.GET)
    public String applyStatusChange(@ModelAttribute("user") User user,
                                    @ModelAttribute("exam") Exam exam,
                                    Model model) {
        Exam selectedExam = examService.getExamById(exam.getId());
        selectedExam.setStatus(exam.getStatus());
        examService.updateExam(selectedExam);
        User preUser = selectedExam.getTeacher();
        List<Exam> examList = examService.getAllExamsOfATeacher(preUser);
        model.addAttribute("user", preUser);
        model.addAttribute("examList", examList);
        return "selectExamToDetermineStatus";
    }


    @RequestMapping(value = "get_exam_report", method = RequestMethod.GET)
    public String getExamReport(@ModelAttribute("user") User user,
                                HttpServletRequest request,
                                Model model) {
        int id = Integer.parseInt(request.getParameter("examId"));
        Exam selectedExam = examService.getExamById(id);
        Course course = selectedExam.getCourse();
        int studentCount = courseService.getNumberOfStudentOfCourse(course);
        List<AnswerSheet> answerSheetList = answerSheetService.getAllAnswerSheetOfExam(selectedExam);
        model.addAttribute("user", user);
        model.addAttribute("studentCount", studentCount);
        model.addAttribute("answerSheetList", answerSheetList);
        model.addAttribute("exam", selectedExam);
        return "examTeacherReport";
    }

    @RequestMapping(value = "choose_descriptive_exam", method = RequestMethod.GET)
    public String getDescriptiveExam(@ModelAttribute("user") User user,
                                     Model model) {
        List<Exam> examList = examService.getAllDescriptiveExamOfTeacher(user);
        List<String> examTitles = examService.getExamTitlesFromList(examList);
        model.addAttribute("user", user);
        model.addAttribute("examList", examList);
        model.addAttribute("examTitles", examTitles);
        model.addAttribute("exam", new Exam());
        return "showDescriptiveExam";
    }

    @RequestMapping(value = "answerSheer-correction-process", method = RequestMethod.GET)
    public String startCorrectionProcess(@ModelAttribute("user") User user,
                                         @ModelAttribute("exam") Exam exam,
                                         Model model) {
        Exam selectedExam = examService.getExamByTitle(exam.getExamTitle());
        List<AnswerSheet> uncorrectedAnswerSheet = answerSheetService.selectUncorrectedAnswerSheet(selectedExam);
        model.addAttribute("user", user);
        if (uncorrectedAnswerSheet.isEmpty()) {
            model.addAttribute("message", "There is no answerSheet to correct");
        } else {
            AnswerSheet answerSheet = uncorrectedAnswerSheet.get(0);
            int number = questionService.giveNextDescriptiveQuestion(0, selectedExam.getQuestions());
            if (number != -1) {
                answerSheet.setQuestionsCounter(number + 1);
                selectedExam.setQuestionCount(selectedExam.getQuestions().size());
                Question question = selectedExam.getQuestions().get(number);
                Double questionScore = selectedExam.getQuestionScoresMap().get(question);
                model.addAttribute("question", question);
                model.addAttribute("questionScore", questionScore);
                model.addAttribute("answerSheet", answerSheet);
                return "correctFirstDescriptiveQuestion";
            } else {
                model.addAttribute("message", "There is no question to correct");
            }
        }
        List<Exam> examList = examService.getAllDescriptiveExamOfTeacher(user);
        List<String> examTitles = examService.getExamTitlesFromList(examList);
        model.addAttribute("user", user);
        model.addAttribute("examList", examList);
        model.addAttribute("examTitles", examTitles);
        model.addAttribute("exam", new Exam());
        return "showDescriptiveExam";

    }

    @RequestMapping(value = "/correctNextQuestion", method = RequestMethod.GET)
    public String showNextQuestionToCorrect(@ModelAttribute("user") User user,
                                            @ModelAttribute("answerSheet") AnswerSheet answerSheet,
                                            HttpServletRequest request,
                                            Model model) {
        int prevQuestionId = Integer.parseInt(request.getParameter("questionId"));
        int answerSheetId = Integer.parseInt(request.getParameter("answerSheetId"));
        Question prevQuestion = questionService.getQuestionById(prevQuestionId);
        int questionCount = answerSheet.getQuestionsCounter();
        AnswerSheet currentAnswerSheet = answerSheetService.getAnswerSheetById(answerSheetId);
        answerSheetService.setUserAndExamIdForAnswerSheet(currentAnswerSheet);
        Exam selectedExam = examService.getExamById(currentAnswerSheet.getExamId());
        model.addAttribute("user", user);

        boolean gradeValidate = questionService.teacherScoringValidate(answerSheet.getGrade(), selectedExam.getQuestionScoresMap().get(prevQuestion));

        if (!gradeValidate) {
            model.addAttribute("message", "Please enter a double number as a question grade less than or equal question score!");
            currentAnswerSheet.setQuestionsCounter(answerSheet.getQuestionsCounter());
            Double questionScore = selectedExam.getQuestionScoresMap().get(prevQuestion);
            model.addAttribute("question", prevQuestion);
            model.addAttribute("questionScore", questionScore);
            model.addAttribute("answerSheet", currentAnswerSheet);
            return "correctNextDescriptiveQuestion";
        }
        Double qGrade = Double.parseDouble(answerSheet.getGrade());

        answerSheetService.putAnswerGradeInList(currentAnswerSheet, prevQuestion, qGrade);
        selectedExam.setQuestionCount(selectedExam.getQuestions().size());

        int number = questionService.giveNextDescriptiveQuestion(questionCount, selectedExam.getQuestions());
        if (number != -1) {

            currentAnswerSheet.setQuestionsCounter(number + 1);
            Question question = selectedExam.getQuestions().get(number);
            Double questionScore = selectedExam.getQuestionScoresMap().get(question);

            model.addAttribute("question", question);
            model.addAttribute("questionScore", questionScore);
            model.addAttribute("answerSheet", currentAnswerSheet);

            System.out.println("questionCount" + questionCount);
            System.out.println("selectedExam.getQuestions().size()" + selectedExam.getQuestions().size());

            if (questionCount +1 < selectedExam.getQuestions().size()) {
                return "correctNextDescriptiveQuestion";
            } else {
                return "correctFinalDescriptiveQuestion";
            }
        }
        model.addAttribute("message", "There is no question to correct");
        List<Exam> examList = examService.getAllDescriptiveExamOfTeacher(user);
        List<String> examTitles = examService.getExamTitlesFromList(examList);

        model.addAttribute("examList", examList);
        model.addAttribute("examTitles", examTitles);
        model.addAttribute("exam", new Exam());
        return "showDescriptiveExam";

    }

    @RequestMapping(value = "/prevQuestionCorrection", method = RequestMethod.GET)
    public String showPreviousCorrectedQuestion(@ModelAttribute("user") User user,
                                                @ModelAttribute("answerSheet") AnswerSheet answerSheet,
                                                HttpServletRequest httpServletRequest,
                                                Model model) {
        int prevQuestionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
        int answerSheetId = Integer.parseInt(httpServletRequest.getParameter("answerSheetId"));

        Question prevQuestion = questionService.getQuestionById(prevQuestionId);
        AnswerSheet currentAnswerSheet = answerSheetService.getAnswerSheetById(answerSheetId);
        answerSheetService.setUserAndExamIdForAnswerSheet(currentAnswerSheet);
        answerSheetService.setUserAndExamIdForAnswerSheet(currentAnswerSheet);
        Exam selectedExam = examService.getExamById(currentAnswerSheet.getExamId());
        int questionCount = answerSheet.getQuestionsCounter();
        System.out.println("questionCount " + questionCount);
        int number = questionService.givePreviousDescriptiveQuestion(questionCount - 1, selectedExam.getQuestions());
        ///////////////////////////////////
        System.out.println("number " + number);

        selectedExam.setQuestionCount(selectedExam.getQuestions().size());

        currentAnswerSheet.setQuestionsCounter(number + 1);
        Question question = selectedExam.getQuestions().get(number);
        Double questionScore = selectedExam.getQuestionScoresMap().get(question);
        model.addAttribute("question", question);
        model.addAttribute("questionScore", questionScore);

        model.addAttribute("user", user);
        model.addAttribute("exam", selectedExam);
        model.addAttribute("answerSheet", currentAnswerSheet);
        if (questionCount > 1) {
            return "correctNextDescriptiveQuestion";
        }
        return "correctFirstDescriptiveQuestion";

    }

    @RequestMapping(value = "/completeCorrection", method = RequestMethod.GET)
    public String endOfTheExamCorrection(@ModelAttribute("user") User user,
                                         @ModelAttribute("answerSheet") AnswerSheet answerSheet,
                                         HttpServletRequest httpServletRequest,
                                         Model model) {
        int prevQuestionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
        int answerSheetId = Integer.parseInt(httpServletRequest.getParameter("answerSheetId"));
        Question prevQuestion = questionService.getQuestionById(prevQuestionId);

        AnswerSheet currentAnswerSheet = answerSheetService.getAnswerSheetById(answerSheetId);
        answerSheetService.setUserAndExamIdForAnswerSheet(currentAnswerSheet);
        Exam selectedExam = examService.getExamById(currentAnswerSheet.getExamId());
        model.addAttribute("user", user);

        boolean gradeValidate = questionService.teacherScoringValidate(answerSheet.getGrade(), selectedExam.getQuestionScoresMap().get(prevQuestion));

        if (!gradeValidate) {
            model.addAttribute("message", "Please enter a double number as a question grade less than or equal question score!");
            currentAnswerSheet.setQuestionsCounter(answerSheet.getQuestionsCounter());
            Double questionScore = selectedExam.getQuestionScoresMap().get(prevQuestion);
            model.addAttribute("question", prevQuestion);
            model.addAttribute("questionScore", questionScore);
            model.addAttribute("answerSheet", currentAnswerSheet);
            return "correctFinalDescriptiveQuestion";
        }
        Double qGrade = Double.parseDouble(answerSheet.getGrade());
        answerSheetService.putAnswerGradeInList(currentAnswerSheet, prevQuestion, qGrade);
        answerSheetService.concludeTotalTeacherScoring(currentAnswerSheet);

        List<Exam> examList = examService.getAllDescriptiveExamOfTeacher(user);
        List<String> examTitles = examService.getExamTitlesFromList(examList);
        model.addAttribute("answerSheet", currentAnswerSheet);
        model.addAttribute("examTitles", examTitles);
        model.addAttribute("exam", selectedExam);
        return "showCorrectionResult";

    }

}
