package com.config;

import com.service.UserService;
import com.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/*").access("hasRole('ROLE_USER')")
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .successHandler((req, res, auth) -> {
                    res.sendRedirect("/");
                })
                .failureHandler((req, res, exp) -> {
                    String error = "BadCredentials!";
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        error = "Error login data.";
                    } else {
                        error = "Unknown error - " + exp.getMessage();
                    }
                    req.getSession().setAttribute("error", error);
                    res.sendRedirect("/login");
                })
                .and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessHandler((req, res, auth) -> {
                    req.getSession().setAttribute("error", "Logout successful");
                    res.sendRedirect("/login");
                }).and().csrf().disable();
    }
}
