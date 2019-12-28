package com.example.readinglist.config;

import com.example.readinglist.Repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Profile是一种条件化配置，基于运行时激活的Profile，会 使用或者忽略不同的Bean或配置类
// @Profile("production")的意思是要求运行时激活 production Profile，才能应用该配置Bean；如果没有激活，就会忽略该配置Bean，进而使用默认的安全配置
//@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 指明，“/”（ReadingListController的方法映射到了该路径）的请求
    // 只有经过身份认证且拥有READER角色的用户才能访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()  // 允许登录请求
                .antMatchers("/reading/**").access("hasRole('READER')") // 如果 "hasRole('READER') 表达式为 true 则允许访问
                .antMatchers("/actuator/shutdown", "/h2-console/**").access("hasRole('ADMIN')")  // 需要 admin 权限
                .antMatchers("/**").authenticated()  // 允许认证过的用户访问
                .and()
                   .formLogin()
                   .loginPage("/login")  // 使用自定义 login 界面
                   .defaultSuccessUrl("/hello/index")
                   .failureUrl("/login?error=true")
                   .permitAll()
                .and()
                  .logout()         // 登出
                  .logoutSuccessUrl("/login?logout=true")
                  .invalidateHttpSession(true)
                  .permitAll();

        // 取消 csrf
//        http.csrf().disable();
        http.csrf().ignoringAntMatchers("/h2-console/**");
        // H2 database console runs inside a frame, So we need to disable X-Frame-Options in Spring Security.
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth); // 使用默认 用户名和密码授权, 测试通过

        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user").password(passwordEncoder.encode("1234")).roles("READER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("1234")).roles("READER", "ADMIN");

//        auth.userDetailsService(new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                Optional<Reader> byId = readerRepository.findById(username);
//                Reader user = byId.get();
//
//                if (user != null) {
//                    return user;
//                }
//                throw new UsernameNotFoundException("Reader '" + username + "' not found.");
//            }
//        });
    }

    // 解决登录报错 There is no PasswordEncoder mapped for the id “null” ： https://blog.csdn.net/qq_21963133/article/details/81066714
    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
