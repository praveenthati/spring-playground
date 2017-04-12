package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic();

        http.authorizeRequests()
                .mvcMatchers("/","/words/**","/lessons/**","/vehicles/**","/flights/**", "/math/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/employees/**").hasAnyRole("EMPLOYEE","MANAGER")
                .anyRequest().authenticated();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("my-admin-password").roles("ADMIN")
                .and()
                .withUser("employee").password("my-employee-password").roles("EMPLOYEE")
                .and()
                .withUser("boss").password("my-boss-password").roles("MANAGER");
    }

}