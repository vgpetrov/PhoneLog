package ru.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.handlers.AccessDenied;
import ru.handlers.AuthenticationFailure;
import ru.handlers.AuthenticationSuccess;
import ru.handlers.Logout;
import ru.service.CustomTokenBasedRememberMeService;

/**
 * Created by vitaly on 17/02/15.
 * <p>
 * Spring security configurations
 */
// TODO: try to implement token auth based on http://habrahabr.ru/post/245415/
@EnableWebMvcSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private String tokenKey = "token";

        @Autowired
        @Qualifier("userDetailsService")
        UserDetailsService userDetailsService;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(rememberMeAuthenticationProvider())
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().access("hasRole('ROLE_USER')")
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                    .and()
                    .httpBasic();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        // Token based authentication
        @Bean
        public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
            return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
        }

        @Bean
        public CustomTokenBasedRememberMeService tokenBasedRememberMeService() {
            CustomTokenBasedRememberMeService service = new CustomTokenBasedRememberMeService(tokenKey, userDetailsService);
            service.setAlwaysRemember(false);
            service.setCookieName("at");
            return service;
        }

        @Bean
        public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
            return new RememberMeAuthenticationProvider(tokenKey);
        }

    }

    @Configuration
    @Order(2)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        @Qualifier("userDetailsService")
        UserDetailsService userDetailsService;

// TODO: already defined in 1 config, how to do different configs
//        @Autowired
//        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        }
//
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/templates/**").permitAll()
                    .antMatchers("/web/**").access("hasRole('ROLE_USER')")
                    .and()
                    .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureHandler(new AuthenticationFailure())
                    .successHandler(new AuthenticationSuccess())
                    .and()
                    .logout().addLogoutHandler(new Logout()).permitAll()
                    .and()
                    .exceptionHandling().accessDeniedHandler(new AccessDenied())
                    .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                    .accessDeniedPage("/accessDenied");
        }
    }

}