package ir.maktab.finalproject.controller;

import ir.maktab.finalproject.model.dto.CourseDto;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.serevice.CourseService;
import ir.maktab.finalproject.serevice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ReferenceController {
    CourseService courseService;
    UserService userService;

    public ReferenceController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }

    @RequestMapping(value = "/managerPanel", method = RequestMethod.GET)
    public String getManagerPanel() {
        return "managerPanel";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String getEditUserPage(Model model) {
        List<User> userList = userService.getAllUserByTest();
        model.addAttribute("userList", userList);
        model.addAttribute("user", new User());
        return "editUser";
    }
    @RequestMapping(value = "/new-course", method = RequestMethod.GET)
    public String getNewCoursePage(Model model) {
        model.addAttribute("course", new Course());
        return "newCourse";
    }
    @RequestMapping(value = "/all-user-in-course", method = RequestMethod.GET)
    public String getSelectCoursePage(Model model) {
        model.addAttribute("course", new Course());
        return "selectCourse";
    }
    @RequestMapping(value = "/delete-course", method = RequestMethod.GET)
    public String getDeleteCoursePage(Model model) {
        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courseList", courseList);
        model.addAttribute("course", new Course());
        return "deleteCourse";
    }
    @RequestMapping(value = "/fill-course", method = RequestMethod.GET)
    public String choiceCourse(Model model) {
        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courseDto", new CourseDto());
        model.addAttribute("courseList", courseList);
        return "choiceCourse";
    }
}
