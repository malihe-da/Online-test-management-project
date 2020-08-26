package ir.maktab.finalproject.controller;


import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.serevice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @RequestMapping(value = "loginProcess", method = RequestMethod.GET)
    public String userLogin(@ModelAttribute("user") User user,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        User oldUser = userService.checkOldUser(user.getEmailAddress(), user.getPassword());

        if (oldUser == null) {
            redirectAttributes.addAttribute("errMsg", "You are not registered yet!");
            redirectAttributes.addAttribute("errorAdditionalDescription", "You are not registered yet!");
            return "redirect:/error";
        }
        if (oldUser.getStatus().equals("unverified")) {
            model.addAttribute("firstName", oldUser.getName());
            return "welcome";
        }

        if (oldUser.getUserRole().equals("teacher")) {
            model.addAttribute("user", oldUser);
            return "teacherPanel";
        }
        model.addAttribute("user", oldUser);
        return "studentPanel";
    }
}
