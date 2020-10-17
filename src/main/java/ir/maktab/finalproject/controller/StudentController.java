package ir.maktab.finalproject.controller;

import ir.maktab.finalproject.model.enums.Status;
import ir.maktab.finalproject.model.enums.Type;
import ir.maktab.finalproject.model.entity.*;
import ir.maktab.finalproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@SessionAttributes("user")
@Controller
public class StudentController {

    private Integer examTimeMinuets;

    ExamService examService;
    UserService userService;
    QuestionService questionService;
    CourseService courseService;
    AnswerSheetService answerSheetService;


    @Autowired
    public StudentController(ExamService examService, UserService userService,
                             QuestionService questionService, CourseService courseService,
                             AnswerSheetService answerSheetService) {
        this.examService = examService;
        this.userService = userService;
        this.questionService = questionService;
        this.courseService = courseService;
        this.answerSheetService = answerSheetService;
    }

    @RequestMapping(value = "/studentPanel", method = RequestMethod.GET)
    public String getStudentPanel(@ModelAttribute("user") User user,
                                  Model model) {

        List<String> courseTitles = courseService.getAllUserCourseTitle(user);

        model.addAttribute("user", user);
        model.addAttribute("course", new Course());
        model.addAttribute("courseTitles", courseTitles);
        return "studentPanel";
    }

    @RequestMapping(value = "/selectCourseToSeeExams", method = RequestMethod.GET)
    public String returnExamsOfSelectCourse(@ModelAttribute("user") User user,
                                            @ModelAttribute("course") Course course,
                                            Model model) {
        Course selectedCourse = courseService.getCoursesByTitle(course.getCourseTitle());
        List<Exam> examList = selectedCourse.getExams();
        List<String> examTitles = examService.getExamTitlesFromList(examList);
        model.addAttribute("user", user);

        if (examList.isEmpty()) {
            model.addAttribute("message", "No exam has been defined for " + course.getCourseTitle() + " Yet!");
            List<String> courseTitles = courseService.getAllUserCourseTitle(user);

            model.addAttribute("course", new Course());
            model.addAttribute("courseTitles", courseTitles);
            return "studentPanel";
        }
        model.addAttribute("selectedCourse", selectedCourse);
        model.addAttribute("exam", new Exam());
        model.addAttribute("examList", examList);
        model.addAttribute("examTitles", examTitles);
        return "showExamsToStudent";
    }

    @RequestMapping(value = "/examStartProcess", method = RequestMethod.GET)
    public String startSelectedExam(@ModelAttribute("user") User user,
                                    @ModelAttribute("exam") Exam exam,
                                    Model model) {
        Exam selectedExam = examService.getExamByTitle(exam.getExamTitle());
        model.addAttribute("user", user);

        if (Objects.equals(selectedExam.getStatus(), Status.stop)) {
            model.addAttribute("message", "The teacher has stopped the exam " + selectedExam.getExamTitle() + " you selected. You are not allowed to participate in it!");
            Course selectedCourse = courseService.getCoursesByTitle(selectedExam.getCourse().getCourseTitle());
            List<Exam> examList = selectedCourse.getExams();
            List<String> examTitles = examService.getExamTitlesFromList(examList);
            model.addAttribute("selectedCourse", selectedCourse);
            model.addAttribute("exam", new Exam());
            model.addAttribute("examList", examList);
            model.addAttribute("examTitles", examTitles);
            return "showExamsToStudent";

        }
        selectedExam.setQuestionCount(selectedExam.getQuestions().size());
        model.addAttribute("exam", selectedExam);
        return "startExamPage";
    }

    @RequestMapping(value = "/examStart", method = RequestMethod.GET)
    public String showFirstQuestion(@ModelAttribute("user") User user,
                                    @ModelAttribute("exam") Exam exam,
                                    HttpServletRequest request,
                                    Model model) {


        User selectedUser = userService.getUserById(user.getId());
        Exam selectedExam = examService.getExamByTitle(exam.getExamTitle());
        if (answerSheetService.isFirstTime(selectedUser, selectedExam)) {
            model.addAttribute("message", "You have already taken the test " + exam.getExamTitle() + " before!");
            List<String> courseTitles = courseService.getAllUserCourseTitle(user);
            model.addAttribute("user", user);
            model.addAttribute("course", new Course());
            model.addAttribute("courseTitles", courseTitles);
            return "studentPanel";
        }
        AnswerSheet answerSheet = new AnswerSheet(user.getId(), selectedExam.getId());
        answerSheet = answerSheetService.saveAnswerSheet(answerSheet);
        answerSheet.setQuestionsCounter(1);
        selectedExam.setQuestionCount(selectedExam.getQuestions().size());
        Question question = selectedExam.getQuestions().get(0);
        question.setQuestionScore(selectedExam.getQuestionScoresMap().get(question));
        List<String> answerOptions = question.getAnswerOptions();
        model.addAttribute("user", user);
        model.addAttribute("exam", selectedExam);
        model.addAttribute("question", question);
        model.addAttribute("answerOptions", answerOptions);
        model.addAttribute("answerSheet", answerSheet);


        examTimeMinuets = selectedExam.getDuration();
        exam.setStart(Calendar.getInstance().getTime());
        request.getSession().setAttribute("examStarted", exam.getStart().getTime());
        final int remaining = getRemainingTime(request);
        model.addAttribute("examTime", remaining);
        return "firstQuestion";
    }

    @RequestMapping(value = "/nextQuestion", method = RequestMethod.GET)
    public String showNextQuestion(@ModelAttribute("user") User user,
                                   @ModelAttribute("answerSheet") AnswerSheet answerSheet,
                                   HttpServletRequest request,
                                   Model model) {
        int prevQuestionId = Integer.parseInt(request.getParameter("questionId"));
        int answerSheetId = Integer.parseInt(request.getParameter("answerSheetId"));
        int remainTime = Integer.parseInt(request.getParameter("remainTime"));
        Question prevQuestion = questionService.getQuestionById(prevQuestionId);
        int questionCount = answerSheet.getQuestionsCounter() + 1;
        AnswerSheet currentAnswerSheet = answerSheetService.getAnswerSheetById(answerSheetId);
        answerSheetService.putNewAnswerInList(currentAnswerSheet, prevQuestion, answerSheet.getAnswer());
        answerSheetService.setStudentAndExamIdForAnswerSheet(currentAnswerSheet);


        Exam selectedExam = examService.getExamById(currentAnswerSheet.getExamId());
        selectedExam.setQuestionCount(selectedExam.getQuestions().size());

        currentAnswerSheet.setQuestionsCounter(questionCount);
        Question question = selectedExam.getQuestions().get(questionCount - 1);
        question.setQuestionScore(selectedExam.getQuestionScoresMap().get(question));
        List<String> answerOptions = question.getAnswerOptions();

        selectedExam.setStart(Calendar.getInstance().getTime());
        request.getSession().setAttribute("examStarted", selectedExam.getStart().getTime());


        model.addAttribute("user", user);
        model.addAttribute("question", question);
        model.addAttribute("answerOptions", answerOptions);
        model.addAttribute("answerSheet", currentAnswerSheet);
        model.addAttribute("examTime", remainTime);
        if (questionCount < selectedExam.getQuestionCount()) {
            return "nextQuestion";
        } else {
            return "finalQuestion";
        }
    }

    @RequestMapping(value = "/prevQuestion", method = RequestMethod.GET)
    public String showPreviousQuestion(@ModelAttribute("user") User user,
                                       @ModelAttribute("answerSheet") AnswerSheet answerSheet,
                                       HttpServletRequest httpServletRequest,
                                       Model model) {
        int prevQuestionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
        int answerSheetId = Integer.parseInt(httpServletRequest.getParameter("answerSheetId"));
        int remainTime = Integer.parseInt(httpServletRequest.getParameter("remainTime"));
        Question prevQuestion = questionService.getQuestionById(prevQuestionId);
        int questionCount = answerSheet.getQuestionsCounter() - 1;
        AnswerSheet currentAnswerSheet = answerSheetService.getAnswerSheetById(answerSheetId);
        answerSheetService.putNewAnswerInList(currentAnswerSheet, prevQuestion, answerSheet.getAnswer());
        answerSheetService.setStudentAndExamIdForAnswerSheet(currentAnswerSheet);

        Exam selectedExam = examService.getExamById(currentAnswerSheet.getExamId());
        selectedExam.setQuestionCount(selectedExam.getQuestions().size());

        currentAnswerSheet.setQuestionsCounter(questionCount);
        Question question = selectedExam.getQuestions().get(questionCount - 1);
        question.setQuestionScore(selectedExam.getQuestionScoresMap().get(question));
        List<String> answerOptions = question.getAnswerOptions();

        model.addAttribute("user", user);
        model.addAttribute("exam", selectedExam);
        model.addAttribute("question", question);
        model.addAttribute("answerOptions", answerOptions);
        model.addAttribute("answerSheet", currentAnswerSheet);
        model.addAttribute("examTime", remainTime);
        if (questionCount > 1) {
            return "nextQuestion";
        } else {
            return "firstQuestion";
        }
    }

    @RequestMapping(value = "/examFinished", method = RequestMethod.GET)
    public String endOfTheExam(@ModelAttribute("user") User user,
                               @ModelAttribute("answerSheet") AnswerSheet answerSheet,
                               HttpServletRequest httpServletRequest,
                               Model model) {
        int prevQuestionId = Integer.parseInt(httpServletRequest.getParameter("questionId"));
        int answerSheetId = Integer.parseInt(httpServletRequest.getParameter("answerSheetId"));
        Question prevQuestion = questionService.getQuestionById(prevQuestionId);

        AnswerSheet currentAnswerSheet = answerSheetService.getAnswerSheetById(answerSheetId);

        answerSheetService.putNewAnswerInList(currentAnswerSheet, prevQuestion, answerSheet.getAnswer());
        answerSheetService.setStudentAndExamIdForAnswerSheet(currentAnswerSheet);
        Exam selectedExam = examService.getExamById(currentAnswerSheet.getExamId());
        if (selectedExam.getExamType().equals(Type.multipleChoice)) {
            answerSheet = answerSheetService.concludeStudentExamScore(currentAnswerSheet);
            model.addAttribute("totalScore", answerSheet.getTotalExamScore());
            model.addAttribute("correctCounter", answerSheet.getCorrectCounter());

        } else {
            model.addAttribute("message", "Wait for the teacher to score your exam " + selectedExam.getExamTitle() + "!");
            model.addAttribute("totalScore", "Not Corrected!");
        }
        model.addAttribute("user", user);
        model.addAttribute("examTitle", currentAnswerSheet.getExam().getExamTitle());
        model.addAttribute("maxScore", currentAnswerSheet.getExam().getExamMaxScore());
        return "showExamResult";

    }

    @RequestMapping(value = "/totalPreviousExamsScore", method = RequestMethod.GET)
    public String getPreviousExamReport(@ModelAttribute("user") User user,
                                        Model model) {

        Map<Exam, Double> finalExamScore = user.getFinalExamScore();
        if (finalExamScore.keySet().size() == 0) {
            model.addAttribute("message", "No test has been registered for you!");

        }
        model.addAttribute("user", user);
        model.addAttribute("examScores", finalExamScore);
        return "showExamReport";

    }


    private int getRemainingTime(HttpServletRequest request) {

        final long start = (long) request.getSession().getAttribute("examStarted");
        final int remaining = (int) ((examTimeMinuets * 60) - ((Calendar.getInstance().getTimeInMillis() - start) / 1000));
        return remaining;
    }

    @RequestMapping(value = "/time")
    @ResponseBody
    public Integer timer(HttpServletRequest request) {
        return getRemainingTime(request);
    }
}
