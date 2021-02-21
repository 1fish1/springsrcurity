package cn.demo.springsrcurity;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class testEncoder {

    @Test
    void encoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("123");
        System.out.println(password + "第一次");
        boolean a = encoder.matches("123",password);
        System.out.println(a);
        String password1 = encoder.encode(password);
        System.out.println(password1 + "第2次");
    }
}
