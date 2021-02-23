package cn.demo.springsrcurity.controller;

import cn.demo.springsrcurity.domain.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * y
 */
@RestController
@RequestMapping("test")
public class testController {

    @RequestMapping("/security")
    public String testSecurity() {
        return "success";
    }

    @RequestMapping("/index")
    public String testIndex() {
        return "index";
    }

    @RequestMapping("/hello")
    public String testHello() {
        return "hello";
    }

    @RequestMapping("/update")
    @Secured("ROLE_admins")
    public String testUpdate() {
        return "update";
    }


    @RequestMapping("/preAuthorize")
    //@PreAuthorize("hasRole('ROLE_管理员')")
    @PreAuthorize("hasAnyAuthority('admins1')")
//    @PostAuthorize("hasAnyAuthority('admins')")
    public String preAuthorize() {
        System.out.println("PostAuthorize");
        return "preAuthorize";
    }


    @RequestMapping("getAll")
    @PreAuthorize("hasAnyAuthority('admins')")
    @PostFilter("filterObject.username == 'admin2'")
    public List<Users> getAllUser(){

        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(1,"admin1","6666"));
        list.add(new Users(2,"admin2","888"));
        System.out.println(list);
        return list;
    }
}
