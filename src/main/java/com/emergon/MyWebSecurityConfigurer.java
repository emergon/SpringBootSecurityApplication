package com.emergon;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()//Restrict access based on the HttpServletRequest
                .antMatchers("/").hasAnyRole("USER", "ADMIN", "TEACHER")//all users will have access to "/"
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/teacher/**").hasRole("TEACHER")
//                .antMatchers("/").permitAll()
                .and()
                .formLogin()//We are going to customize the form login process
                .loginPage("/loginPage")//Show my form at this request
                .loginProcessingUrl("/authenticate")//Login form will POST data to this URL to check username and password
                .permitAll()//Allow everyone to see login page. Don't have to be logged in.
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}1234").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}1234").roles("ADMIN", "USER")
//                .and()
//                .withUser("teacher").password("{noop}1234").roles("TEACHER");
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
        
    }
    
    @Bean//This manager is used by Spring Security to automatically store Users in  Default DB.
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        return manager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
