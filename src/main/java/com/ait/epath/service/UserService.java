package com.ait.epath.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ait.epath.model.Role;
import com.ait.epath.model.User;
import com.ait.epath.repository.RoleRepository;
import com.ait.epath.repository.UserRepository;

@Service("userService")
public class UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, 
    					RoleRepository roleRepository, 
    					BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	public User findUserByEmail(String email) {
	     return userRepository.findByEmail(email);
	}
	
	 public User saveUser(User user) {
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setActive(2);
	        Role userRole = roleRepository.findByRole("USER");
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        return userRepository.save(user);
	 }

	 public Role findRolebyEmail(String email) {
	    	
		 	return roleRepository.findRoleByUserEmail(email);
	 
	 }
}
