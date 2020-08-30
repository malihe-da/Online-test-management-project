package ir.maktab.finalproject.controller;

import ir.maktab.finalproject.model.dao.ConfirmationTokenDao;
import ir.maktab.finalproject.model.dao.UserDao;
import ir.maktab.finalproject.model.entity.ConfirmationToken;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.serevice.EmailSenderService;
import ir.maktab.finalproject.serevice.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserAccountController {
    UserDao userDao;
    UserService userService;
    ConfirmationTokenDao confirmationTokenDao;
    EmailSenderService emailSenderService;

    public UserAccountController(UserDao userDao, UserService userService,
                                 ConfirmationTokenDao confirmationTokenDao,
                                 EmailSenderService emailSenderService) {
        this.userDao = userDao;
        this.userService = userService;
        this.confirmationTokenDao = confirmationTokenDao;
        this.emailSenderService = emailSenderService;
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "registerProcess", method = RequestMethod.GET)
    public String addUser(@ModelAttribute("user") User user,
                          HttpServletRequest request,
                          Model model) {

        if (userService.duplicateMailAddress(user.getEmailAddress())) {
            model.addAttribute("errorMsg", "You cannot enter duplicate mailing addresses");
            return "error";
        }
        if (!userService.isValidPassword(user.getPassword())) {
            model.addAttribute("errorMsg", "Your password is not valid");
            return "error";
        }
        userService.userRegister(user);
        emailSenderService.sendEmailToAddress(user);

        model.addAttribute("emailId", user.getEmailAddress());
        return "successfulRegistration";
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userDao.findByEmailAddressIgnoreCase(token.getUser().getEmailAddress());
            user.setEnabled(true);
            userDao.save(user);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("errorMsg", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }


    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String showError2(Model model, @RequestParam("errorMsg") String errorMsg) {
        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }

}
