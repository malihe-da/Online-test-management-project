package ir.maktab.finalproject.controller;

import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class restController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/showStudents", produces = "application/json")
    public List<User> getAllStudent(){
        return userService.getAllStudents();
    }

    @PostMapping(value = "/searchUser", consumes = "application/json"/*, produces = "application/json"*/)
    public String getAllStudentByPost(@RequestBody User user){
        System.out.println(user.toString());
        return "Hello";
    }

}
