package com.ait.epath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ait.epath.model.User;
import com.ait.epath.repository.UserRepository;
import com.ait.epath.service.UserService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	 private UserService userService;

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public ModelAndView openRegistration(User user, Model model) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("register");
		
		return modelAndView;
		
	}
	
	/*==============
	 * Register new User
	 *  - Search user by email
	 *  - If user don't exist create new user with object user passed in the form
	 *  - If user exists. send a message saying the user exist.
	 */
	@PostMapping
	public ModelAndView createNewUser(User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		
		User userExist = userService.findUserByEmail(user.getEmail());
		
		if (userExist != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");

        }
		
		return modelAndView;
	}
}
