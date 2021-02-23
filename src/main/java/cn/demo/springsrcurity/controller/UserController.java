package cn.demo.springsrcurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("/login")
    public String  testSecurity(){
        return "login";
    }

}
