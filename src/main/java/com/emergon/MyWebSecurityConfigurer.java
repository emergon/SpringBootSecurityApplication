package com.emergon;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MyWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()//Restrict access based on the HttpServletRequest
                .antMatchers("/").hasAnyRole("USER", "ADMIN")//all users will have access to "/"
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()//We are going to customize the form login process
                .loginPage("/loginPage")//Show my form at this request
                .loginProcessingUrl("/authenticate")//Login form will POST data to this URL to check username and password
                .permitAll()//Allow everyone to see login page. Don't have to be logged in.
                .and()
                .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}1234").roles("USER")
                .and()
                .withUser("admin").password("{noop}1234").roles("ADMIN", "USER");
    }

}
