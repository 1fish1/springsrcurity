package cn.demo.springsrcurity.service;

import cn.demo.springsrcurity.Mapper.UsersMapper;
import cn.demo.springsrcurity.domain.Users;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserDetailsService")
public class MyUserService implements UserDetailsService {


//        @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
////        工具类
//        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("roles");
//        return new User("mary",new BCryptPasswordEncoder().encode("123"),auth);
//    }
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        Users users = usersMapper.selectOne(wrapper);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        System.out.println(users.toString());
        List<GrantedAuthority> auths =
                AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(users.getUsername(),
                new BCryptPasswordEncoder().encode(users.getPassword()), auths);
    }
}
