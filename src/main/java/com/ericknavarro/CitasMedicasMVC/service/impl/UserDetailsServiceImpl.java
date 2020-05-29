/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ericknavarro.CitasMedicasMVC.service.impl;
import com.ericknavarro.CitasMedicasMVC.dao.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository repository;
   
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        com.ericknavarro.CitasMedicasMVC.model.User appUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

        List grantList = new ArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.getRole());
        grantList.add(grantedAuthority);

        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
        UserDetails userD = (UserDetails) new User(appUser.getUsername(), passwordEncoder().encode(appUser.getPassword()), grantList);
        
        return userD;
    }
}
    

