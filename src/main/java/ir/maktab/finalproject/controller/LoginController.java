package ir.maktab.finalproject.controller;

import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.serevice.CourseService;
import ir.maktab.finalproject.serevice.ManagerService;
import ir.maktab.finalproject.serevice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@SessionAttributes("user")
@Controller
public class LoginController {

    ManagerService managerService;
    CourseService courseService;
    UserService userService;

    public LoginController(ManagerService managerService, CourseService courseService, UserService userService) {
        this.managerService = managerService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User setUpUserForm() {
        return new User();
    }
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLoginPage(Model model) {
       // model.addAttribute("user", new User());
        return "login";
    }


    @RequestMapping(value = "loginProcess", method = RequestMethod.GET)
    public String userLogin(@ModelAttribute("user") User user,
                            Model model) {
        User oldUser = userService.checkOldUser(user.getEmailAddress(), user.getPassword());

        if (oldUser == null) {
            model.addAttribute("errorMsg", "You are not registered yet!!");
            return "error";
        }
        if (oldUser.getStatus().equals("unverified")) {
            model.addAttribute("user", oldUser);
            return "welcome";
        }

        if (oldUser.getUserRole().equals("teacher")) {
            List<Course> courseList = oldUser.getCourses();
            model.addAttribute("user", oldUser);
            return "teacherPanel";
        }
        model.addAttribute("user", oldUser);
        return "studentPanel";
    }

}
