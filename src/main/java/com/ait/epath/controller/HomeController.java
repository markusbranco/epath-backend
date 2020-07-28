package com.ait.epath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ait.epath.model.Role;
import com.ait.epath.model.User;
import com.ait.epath.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	
		@Autowired
		private UserService userService;
	
	   @GetMapping
	    public ModelAndView home(){
	        ModelAndView modelAndView = new ModelAndView();
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        Role role = userService.findRolebyEmail(auth.getName());
	        System.out.println("auth: " + auth.getName());
	        
	        if (role.getId() == 1) {
	        	 modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
	             modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
	             modelAndView.setViewName("/admin/home");
	        } else {
	        	
	        	userHome(user, modelAndView);
	        	
	       }
	        
	       return modelAndView;
	    }
	   
		public ModelAndView userHome(User user, ModelAndView modelAndView) {
	        
	        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
	        modelAndView.addObject("adminMessage","Content Available Only for Users ");
	        modelAndView.setViewName("/user/home");
	        //listAllUsers(modelAndView);
	        
	        return modelAndView;
	        

		}

}
