 package com.stasiuksv.prototype.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stasiuksv.prototype.controllers.User;
import com.stasiuksv.prototype.model.RoleEntity;
import com.stasiuksv.prototype.model.UserEntity;

import java.util.HashSet;
import java.util.Set;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
	@Autowired
	private DataService<User,UserEntity> userService;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
    {

    	UserEntity user = userService.getByName(userName);
        Set<GrantedAuthority> roles = new HashSet<>();
        for (RoleEntity role:user.getRoles())
        	roles.add(new SimpleGrantedAuthority(role.getRoleName()));
 
      
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUserName(),
                                                                       user.getPassWord(),
                                                                       roles);
        return userDetails;
    }
 
}
