package ir.maktab.finalproject.controller;


import ir.maktab.finalproject.model.entity.Manager;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.service.CourseService;
import ir.maktab.finalproject.service.ManagerService;
import ir.maktab.finalproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ManagerController {

    ManagerService managerService;
    CourseService courseService;
    UserService userService;
    private Model model;

    public ManagerController(ManagerService managerService, CourseService courseService, UserService userService) {
        this.managerService = managerService;
        this.courseService = courseService;
        this.userService = userService;
    }


    @RequestMapping(value = "managerLogin", method = RequestMethod.GET)
    public String managerLogin(Model model) {
        model.addAttribute("manager", new Manager());
        return "managerLogin";
    }

    @RequestMapping(value = "managerLoginProcess", method =RequestMethod.GET)
    public String managerLoginProcess(@ModelAttribute("manager") Manager manager,
                            Model model) {

        Manager oldManager = managerService.checkOldManager(manager.getEmailAddress(), manager.getPassword());
        if (oldManager == null) {
            model.addAttribute("errorMsg", "Invalid email address or password!");
            return "error";
        }

        model.addAttribute("manager", oldManager);
        return "managerPanel";
    }


    @RequestMapping(value = "/search_in_users", method = RequestMethod.GET)
    public String showUserSearchPage(Model model) {
        model.addAttribute("user", new User());
        return "userSearch";
    }


    @RequestMapping(value = "/userSearchProcess", method = RequestMethod.GET)
    public String searchUser(@ModelAttribute("user") User user, Model model) {
        List<User> userList = userService.findMaxMatch(user.getId(),user.getUserRole(),
                user.getName(), user.getFamily(), user.getStatus());
        model.addAttribute("userList", userList);
        System.out.println(userList.toString());
        return "showAllUsers";
    }
    @RequestMapping(value = "/editUserProcess", method = RequestMethod.GET)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        User prevUser = userService.getUserByEmailAddress(user.getEmailAddress());
        return new ModelAndView("editUser", "user", prevUser);
    }


    @RequestMapping(value = "/editUserInfoProcess", method = RequestMethod.GET)
    public String editUserInfo(@ModelAttribute("user") User user, Model model) {
        if(user.getUserRole()!=null) {
            userService.changeUserRole(user.getId(), user.getUserRole());
        }
        if(user.getStatus()!=null) {
            userService.changeUserStatus(user.getId(), user.getStatus());
        }
        List<User> userList = userService.getAllUserByTest();
        model.addAttribute("userList", userList);
        return "showAllUsers";
    }

    @RequestMapping(value = "/show_all_student", method = RequestMethod.GET)
    public String showAllStudent(Model model) {
        List<User> userList = userService.getAllStudents();
        model.addAttribute("userList", userList);
        return "showAllUsers";
    }

    @RequestMapping(value = "/show_all_teacher", method = RequestMethod.GET)
    public ModelAndView showAllTeacher(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = userService.getAllTeachers();
        ModelAndView mav = new ModelAndView("showAllUsers");
        mav.addObject("userList", userList);
        return mav;
    }


}
