package com.kudiukin.homework10.configuration;

import com.kudiukin.homework10.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private PersonService personService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] antMatchersForAdmin = {"/api/person/all", "/api/cart/clean", "/api/person/get", "/api/person/delete",
                "/api/product/create", "/api/product/update", "/api/product/delete", "/api/cart/all", "api/cart/delete",
                "/api/shop/create", "/api/shop/delete"};
        String[] antMatchersForCustomer = {"/api/person/create", "/api/person/update", "/api/product/all", "/api/product/get",
                "/api/cart/create", "/api/cart/get", "api/cart/add", "/api/cart/remove", "api/cart/clean",
                "/api/shop/all/", "/api/shop/get"};
        http.headers().frameOptions().disable();
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/index", "/registration", "/api/shop/all", "/api/product/all").permitAll()
                .antMatchers(antMatchersForAdmin).hasRole("ADMIN")
                .antMatchers(antMatchersForCustomer).hasAnyRole("ADMIN", "CUSTOMER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService).passwordEncoder(passwordEncoder);
    }
}
