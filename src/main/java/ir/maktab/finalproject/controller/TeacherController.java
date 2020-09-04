package ir.maktab.finalproject.controller;


import ir.maktab.finalproject.model.dao.ConfirmationTokenDao;
import ir.maktab.finalproject.model.dao.UserDao;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.serevice.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SessionAttributes("user")
@Controller
public class TeacherController {

    ExamService examService;
    UserService userService;
    QuestionService questionService;
    CourseService courseService;
    QuestionBankService questionBankService;

    public TeacherController(UserService userService,
                             ExamService examService,
                             QuestionService questionService,
                             CourseService courseService,
                             QuestionBankService questionBankService) {
        this.userService = userService;
        this.questionService = questionService;
        this.examService = examService;
        this.courseService = courseService;
        this.questionBankService = questionBankService;
    }

    @RequestMapping(value = "/teacherPanel", method = RequestMethod.GET)
    public String getManagerPanel(@ModelAttribute("user") User user,
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
    ////////////////////rest////////////////////////////

    @GetMapping(value = "/examClassification")
    public @ResponseBody
    List<Exam> getQuestionByPath(HttpServletRequest request, HttpServletResponse response) {
        String classification = request.getParameter("choice");
        List<Exam> examList = examService.getExamsByClassification(classification);
        System.out.println(examList.toString());
        System.out.println("salam");
        return examList;
    }

    ///////////////////////////////////////////////

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
        if (selectedExam.getExamAuthorId() == user.getId()) {
            // List<Question> bankQuestions = questionBankService.getClassifiedQuestionsInBank(selectedExam.getExamClassification());
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

        Question selectedQuestion = questionService.getQuestionByQuestionFace(question.getQuestionFace());

        int examId = Integer.parseInt(httpServletRequest.getParameter("selectedExamId"));
        Exam selectedExam = examService.getExamById(examId);

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
        //question.setExam(exam);
        questionService.saveNewQuestion(question);
        examService.addQuestionToExam(question, selectedExam);
        List<Question> bankQuestions = questionBankService.getQuestionsInBank();
        if (question.getQuestionBankAdded().equals("yes")) {
            questionBankService.addQuestionIdToBank(question);
        }
        model.addAttribute("bankQuestions", bankQuestions);
        model.addAttribute("user", user);
        model.addAttribute("question", new Question());
        model.addAttribute("exam", selectedExam);
        return "editSelectedExam";

    }

    @RequestMapping(value = "scoreDetermine", method = RequestMethod.GET)
    public String endOfQuestionPlanning(@ModelAttribute("user") User user,
                                        HttpServletRequest httpServletRequest,
                                        Model model) {
        int examId = Integer.parseInt(httpServletRequest.getParameter("selectedExamId"));
        Exam selectedExam = examService.getExamById(examId);
        List<Question> questionList = selectedExam.getQuestions();
        model.addAttribute("user", user);
        model.addAttribute("questionList", questionList);
        model.addAttribute("exam", selectedExam);
        return "scoreDetermine";

    }

    @RequestMapping(value = "examScoreComplete", method = RequestMethod.GET)
    public String endOfQuestionScore(@ModelAttribute("user") User user,
                                     @ModelAttribute("exam") Exam exam,
                                     Model model) {
        List<Double> questionsScore = exam.getQuestionScores();
        Exam prevExam = examService.getExamById(exam.getId());
        Exam selectedExam = examService.saveQuestionsScore(prevExam, questionsScore);
        List<Question> questionList = selectedExam.getQuestions();
        System.out.println(exam.getQuestionScores());
        System.out.println(exam.getExamMAxScore());
        model.addAttribute("user", user);
        model.addAttribute("questionList", questionList);
        model.addAttribute("exam", selectedExam);
        return "showScores";

    }

}
