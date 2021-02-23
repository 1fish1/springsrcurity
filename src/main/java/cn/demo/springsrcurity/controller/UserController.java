package cn.demo.springsrcurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("/login")
    public String  testSecurity(){
        return "login";
    }

}
