package cn.demo.springsrcurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class securityConfigTest extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private DataSource dataSource;
    @Autowired
    private PersistentTokenRepository tokenRepository;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new
                JdbcTokenRepositoryImpl();
// 赋值数据源
        jdbcTokenRepository.setDataSource(dataSource);
// 自动创建表,第一次执行会创建，以后要执行就要删除掉！
      //  jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEnco());
    }

    // 配置自己的登陆页面和。。。
    protected void configure(HttpSecurity http) throws Exception {
        // 退出登陆按钮 这个地方有个需要注意的地方，退出登陆后如果访问其他页面还是可以访问的话，
        // 说明页面的缓存没有清除。清掉缓存就会返回登陆页面
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/preAuthorize").permitAll();

        http.exceptionHandling().accessDeniedPage("/unAuth.html");

        http.formLogin() //自定义自己登陆的页面
                /**
                 * .loginPage("/login.html") //登陆页面设置
                 * .loginProcessingUrl("/user/login") //登陆访问路径
                 * 这两个配置路径要相同， 要不然会出现登陆数据正确但是报错404
                 */
                .loginPage("/login.html") //登陆页面设置
                .loginProcessingUrl("/user/login") //登陆访问路径
//                .defaultSuccessUrl("/test/index").permitAll()//登陆成功后跳转路径
//                 退出按钮
                .defaultSuccessUrl("/success.html").permitAll()
                .and().authorizeRequests()
                .antMatchers("/", "/test/hello", "/user/login").permitAll() //设置哪些页面可以直接访问 不需要认证
                /**
                 * List<GrantedAuthority> auths =
                 *                 AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
                 *                 和myUserService  中获取到的用户角色相关
                 */
                /**当前登陆的用户只要具有admins的权限才可以访问该页面
                 如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true.
                 .antMatchers("/test/hello").hasAuthority("admins")
                 */
                /**
                 *如果当前的主体有任何提供的角色（给定的作为一个逗号分隔的字符串列表）的话，返回true.
                 * 多个权限
                 * .antMatchers("/test/hello").hasAnyAuthority("admins", "roles")
                 */
                /**
                 * 如果用户具备给定角色就允许访问,否则出现 403
                 * 如果当前主体具有指定的角色，则返回 true。
                 * .antMatchers("/test/hello").hasRole("sale")
                 */
                /**
                 * 如果用户具备给定角色就允许访问,否则出现 403
                 * 如果当前主体具有指定的角色，则返回 true。
                 * .antMatchers("/test/hello").hasAnyRole("sale","any")
                 */
                .antMatchers("/test/update").hasAuthority("admins")
//                .antMatchers("/test/hello").hasAnyAuthority("admins", "roles")
                //.antMatchers("/test/hello").hasRole("sale")
                // .antMatchers("/test/hello").hasAnyRole("sale","any")
                .anyRequest().authenticated()
                // 开启记住我功能
                .and().rememberMe()
                .tokenValiditySeconds(600)
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .and().csrf().disable(); //关闭csrf



    }

    @Bean
    PasswordEncoder passwordEnco() {
//     返回new 接口的实现类
        return new BCryptPasswordEncoder();
    }
}
