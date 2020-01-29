package com.example.springsecuritydemo;

import com.example.springsecuritydemo.db.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")   // 角色检查
                .antMatchers("/user/**").hasRole("USER")   // 角色检查
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                //.anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
        super.configure(http);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() { //覆盖写userDetailsService方法
        return new CustomUserDetailService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
        //auth.inMemoryAuthentication()
        //        .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
        //        .and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER");
    }
}
