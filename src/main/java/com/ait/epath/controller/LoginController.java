package com.ait.epath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ait.epath.repository.UserRepository;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping
	public ModelAndView login(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//model.addAttribute("message", "Hello, World!");
		
		modelAndView.setViewName("login");
		
		return modelAndView;
	}
	
}
