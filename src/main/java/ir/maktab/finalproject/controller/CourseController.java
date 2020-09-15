package ir.maktab.finalproject.controller;

import ir.maktab.finalproject.model.dto.CourseDto;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.service.CourseService;
import ir.maktab.finalproject.service.ManagerService;
import ir.maktab.finalproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    CourseService courseService;
    UserService userService;
    ManagerService managerService;

    public CourseController(CourseService courseService, UserService userService, ManagerService managerService) {
        this.courseService = courseService;
        this.userService = userService;
        this.managerService = managerService;
    }

    @RequestMapping(value = "/search_in_courses", method = RequestMethod.GET)
    public String showCourseSearchPage(Model model) {
        model.addAttribute("course", new Course());
        return "courseSearch";
    }

    @RequestMapping(value = "/courseSearchProcess", method = RequestMethod.GET)
    public String searchCourse(@ModelAttribute("course") Course course, Model model) {
        List<Course> courseList = courseService.findCourseMaxMatch(course.getId(), course.getCourseTitle(),
                course.getCourseClassification());
        model.addAttribute("courseList", courseList);
        return "showAllCourses";
    }

    @RequestMapping(value = "/show_all_courses", method = RequestMethod.GET)
    public String showAllCourses(Model model) {
        List<Course> courseList = courseService.findCourseMaxMatch(null,null, null);
        System.out.println(courseList.toString());
        model.addAttribute("courseList", courseList);
        return "showAllCourses";
    }

    @RequestMapping(value = "/newCourseProcess", method = RequestMethod.GET)
    public String addNewCourse(@ModelAttribute("course") Course course, Model model) {
        if(courseService.checkCourseTitleIsUnique(course.getCourseTitle())) {
            courseService.addNewCourse(course);
        }
        List<Course> courseList = courseService.getAllCourses();
        System.out.println(courseList.toString());
        model.addAttribute("courseList", courseList);
        return "showAllCourses";
    }

    @RequestMapping(value = "/selectCourse", method = RequestMethod.GET)
    public String findUserSInCourse(@ModelAttribute("course") Course course, Model model) {
        List<Course> courseList = courseService.findCourseMaxMatch(course.getId(), course.getCourseTitle(),
                course.getCourseClassification());
        System.out.println(courseList.toString());
        List<User> userList= userService.getAllUserInCourses(courseList);
        model.addAttribute("course", new Course());
        model.addAttribute("userList", userList);
        model.addAttribute("courseList", courseList);
        return "selectCourse";
    }

    @RequestMapping(value = "/deleteCourseProcess", method = RequestMethod.GET)
    public String deleteCourse(@ModelAttribute("course") Course course, Model model) {
        if(course.getId()>0){
            Course choice = courseService.getCoursesById(course.getId());
            courseService.deleteCourse(course);
        }else if(course.getCourseTitle()!=null){
            Course choice = courseService.getCoursesByTitle(course.getCourseTitle());
            courseService.deleteCourse(course);
        }
        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courseList", courseList);
        return "showAllCourses";
    }
    @RequestMapping(value = "/choiceCourseProcess", method = RequestMethod.GET)
    public String AddUsers(@ModelAttribute("courseDto") CourseDto courseDto, Model model) {
        Course selected = courseService.getCoursesById(courseDto.getCourseId());
        List<User> userList = userService.getAllUserByTest();
        List<Course> courseList = new ArrayList<>();
        courseList.add(selected);
        System.out.println(selected.toString());
        model.addAttribute("userList", userList);
        model.addAttribute("courseDto", courseDto);
        model.addAttribute("courseList", courseList);
        return "addUserToCourse";
    }
    @RequestMapping(value = "/addUserProcess", method = RequestMethod.GET)
    public String AddUsersToCourse(@ModelAttribute("courseDto") CourseDto courseDto, Model model) {
        Course selectedCourse = courseService.getCoursesById(courseDto.getCourseId());
        User selectedUser = userService.getUserById(courseDto.getUserId());
        managerService.addUserToCourse(selectedUser, selectedCourse);
        Course selected = courseService.getCoursesById(courseDto.getCourseId());
        List<User> userList = userService.getAllUserByTest();
        List<Course> courseList = new ArrayList<>();
        courseList.add(selected);
        model.addAttribute("userList", userList);
        model.addAttribute("courseDto", courseDto);
        model.addAttribute("courseList", courseList);
        return "addUserToCourse";
    }
}
