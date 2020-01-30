package com.example.springsecuritydemo;

import com.example.springsecuritydemo.db.CustomUserDetailService;
import com.example.springsecuritydemo.jwt.CustomAuthenticationProvider;
import com.example.springsecuritydemo.jwt.JWTAuthenticationFilter;
import com.example.springsecuritydemo.jwt.JWTLoginFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")   // 角色检查
                .antMatchers("/user/**").hasAuthority("USER")   // 角色检查
                .antMatchers("/**").hasAnyAuthority("ADMIN","USER")
                //.anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);;
        //super.configure(http);
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
        auth.authenticationProvider(new CustomAuthenticationProvider());
//        auth.userDetailsService(userDetailsService());
        //auth.inMemoryAuthentication()
        //        .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
        //        .and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER");
    }
}
