package cn.demo.springsrcurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * y
 */
@RestController
@RequestMapping("test")
public class testController {

    @RequestMapping("/security")
    public String  testSecurity(){
        return "success";
    }

}
