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
import java.util.List;

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
        List<Course> courseList = courseService.getAllCourses();
        List<Exam> examList = examService.getAllExam();
        model.addAttribute("user", user);
        model.addAttribute("course", new Course());
        model.addAttribute("examList", examList);
        model.addAttribute("courseList", courseList);
        return "showAllExam";
    }

    @RequestMapping(value = "showSelectedCourseExam", method = RequestMethod.GET)
    public String showAllExam(@ModelAttribute("user") User user,
                              @ModelAttribute("course") Course course,
                              Model model) {
        Course selectedCourse = courseService.getCourseByClassification(course.getCourseClassification());
        // List<Exam> examList = examService.getAllExamOfACourse(selectedCourse);
        List<Exam> examList = examService.getAllExam();
        List<Course> courseList = courseService.getAllCourses();
        //status.setComplete();
        model.addAttribute("user", user);
        model.addAttribute("course", new Course());
        model.addAttribute("courseList", courseList);
        model.addAttribute("examList", examList);
        model.addAttribute("exam", new Exam());
        return "showAllExam";
    }

    @RequestMapping(value = "editExam", method = RequestMethod.GET)
    public String selectExamToEdit(@ModelAttribute("user") User user,
                                   HttpServletRequest httpServletRequest,
                                   Model model) {
        int id = Integer.parseInt(httpServletRequest.getParameter("examId"));
        Exam selectedExam = examService.getExamById(id);
        if (selectedExam.getExamAuthorId() == user.getId()) {
            List<Question> bankQuestions = questionBankService.getQuestionsInBank();
            model.addAttribute("bankQuestions", bankQuestions);
            model.addAttribute("user", user);
            model.addAttribute("question", new Question());
            model.addAttribute("exam", selectedExam);
            return "editSelectedExam";
        } else {
            model.addAttribute("message", "You select the other author to edit. It is not possible");
            List<Exam> examList = examService.getAllExam();
            List<Course> courseList = courseService.getAllCourses();
            model.addAttribute("courseList", courseList);
            model.addAttribute("user", user);
            model.addAttribute("examList", examList);
            model.addAttribute("exam", new Exam());
            return "showAllExam";
        }
    }

    @RequestMapping(value = "new-exam", method = RequestMethod.GET)
    public String defineNewExam(@ModelAttribute("user") User user,
                                Model model) {
        model.addAttribute("user", user);
        model.addAttribute("exam", new Exam());
        return "newExam";
    }

    @RequestMapping(value = "add-newExam", method = RequestMethod.GET)
    public String saveNewExam(@ModelAttribute("user") User user,
                              @ModelAttribute("exam") Exam exam,
                              Model model) {
        examService.saveExam(exam);
        model.addAttribute("message", "The test was successfully registered. " +
                "Please proceed to the exam editing portal to complete it");
        model.addAttribute("user", user);
        model.addAttribute("exam", new Exam());
        return "newExam";
    }

    @RequestMapping(value = "addSelectedQuestionToExam", method = RequestMethod.GET)
    public String addQuestionFromBankToExam(@ModelAttribute("user") User user,
                                            HttpServletRequest httpServletRequest,
                                            Model model) {
        int questionId = Integer.parseInt(httpServletRequest.getParameter("questionFromBank"));
        Question selectedQuestion = questionService.getQuestionById(questionId);

        int examId = Integer.parseInt(httpServletRequest.getParameter("selectedExamId"));
        Exam selectedExam = examService.getExamById(examId);

        examService.addQuestionToExam(selectedQuestion, selectedExam);
        List<Question> bankQuestions = questionBankService.getQuestionsInBank();
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
        if(question.getQuestionBankAdded().equals("yes")){
            questionBankService.addQuestionIdToBank(question.getId());
        }
        model.addAttribute("bankQuestions", bankQuestions);
        model.addAttribute("user", user);
        model.addAttribute("question", new Question());
        model.addAttribute("exam", selectedExam);
        return "editSelectedExam";

    }

    @RequestMapping(value = "endOfQuestionPlanning", method = RequestMethod.GET)
    public String endOfQuestionPlanning(@ModelAttribute("user") User user,
                                           Model model) {
        model.addAttribute("user", user);
        return "teacherPanel";

    }

}
