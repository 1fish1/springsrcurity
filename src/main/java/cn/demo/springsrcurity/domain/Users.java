package cn.demo.springsrcurity.domain;

import lombok.Data;

@Data
public class Users {
    private Integer id;
    private String username;
    private String password;
}
