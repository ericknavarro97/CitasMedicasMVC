/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.CitasMedicasMVC.springsecurity;

import com.ericknavarro.CitasMedicasMVC.dao.UserRepository;
import com.ericknavarro.CitasMedicasMVC.service.impl.UserDetailsServiceImpl;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


/**
 *
 * @author Usuario
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    UserRepository repo;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/citas")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/login?logout");
    }
    
    /*@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.
        auth.inMemoryAuthentication()
          .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
          .and()
          .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
          .and()
          .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }*/
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
    
}
