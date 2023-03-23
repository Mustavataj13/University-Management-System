package com.university.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET)
                .permitAll()
                .antMatchers("/student/*")
                .permitAll()
                .antMatchers("/user/register")
                .permitAll()
                .antMatchers("/user/grade")
                .permitAll()
                .antMatchers("/user/enrollment")
                .hasAuthority("USER")
                .antMatchers("/faculty/*")
                .hasAuthority("ADMIN")
                .antMatchers("/enrollment/*")
                .hasAuthority("ADMIN")
                .antMatchers("/department/save")
                .hasAuthority("ADMIN")
                .antMatchers("/course/*")
                .hasAuthority("ADMIN")
                .antMatchers("/student/courses")
                .hasAnyAuthority("FACULTY", "ADMIN")
                .antMatchers("/student/grades")
                .hasAnyAuthority("FACULTY", "ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled from user WHERE username = ?")
                .authoritiesByUsernameQuery("select username, role from roles WHERE username = ?");

    }

}