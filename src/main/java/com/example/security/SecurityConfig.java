package com.example.security;

import com.example.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeDetailsService employeeDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .mvcMatchers("/","/me/**","/words/**","/lessons/**","/vehicles/**","/flights/**", "/math/**").permitAll()
                .mvcMatchers("/admin/**").hasAnyRole("MANAGER","ADMIN")
                .mvcMatchers("/employees/**").hasAnyRole("EMPLOYEE","MANAGER")
                .anyRequest().authenticated();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin").password("my-admin-password").roles("ADMIN")
//                .and()
//                .withUser("employee").password("my-employee-password").roles("EMPLOYEE")
//                .and()
//                .withUser("boss").password("my-boss-password").roles("MANAGER");

        auth.userDetailsService(employeeDetailsService)
                .passwordEncoder(passwordEncoder());// this is to encode password with bCrypt
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}