package com.example.springsecuritydemo;

import com.example.springsecuritydemo.db.CustomUserDetailService;
import com.example.springsecuritydemo.jwt.CustomAuthenticationProvider;
import com.example.springsecuritydemo.jwt.JWTAuthenticationFilter;
import com.example.springsecuritydemo.jwt.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .antMatchers("/**").authenticated()
//                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
    }


    // 需要配置这个支持password模式 , support password grant type
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    // 支持 OAuth2 的密码编码方式， 因为OAuth2 不支持 BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return Objects.equals(charSequence.toString(),s);
            }
        };
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() { //覆盖写userDetailsService方法
        return new CustomUserDetailService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(new CustomAuthenticationProvider());
//        auth.userDetailsService(userDetailsService());
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER");
    }
}
