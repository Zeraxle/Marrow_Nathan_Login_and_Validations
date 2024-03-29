package com.nathanm.loginandvalidation.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nathanm.loginandvalidation.models.LoginUser;
import com.nathanm.loginandvalidation.models.User;
import com.nathanm.loginandvalidation.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	@Autowired
	private UserService service;
	
	@Autowired 
	private HttpSession session;
	
	@GetMapping("/")
	public String loginAndRegistration(@ModelAttribute("newUser") User user, Model model) {
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User user, BindingResult result, 
			Model model) {
		
		User takenEmail = service.get(user.getEmail());
		User takenUsername = service.getByUsername(user.getUsername());
		
		if (takenEmail != null) {
			result.rejectValue("email", "unique", "Email is already taken");
		}
		
		 if (takenUsername != null) {
			result.rejectValue("username", "taken", "Username is already taken");
		 }
		 
		 if(!user.getPassword().equals(user.getConfirmPassword())) {
			    result.rejectValue("confirmPassword", "Matches", "Confirm Password must match Password!");
			}
		 
		 if (result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		 
		String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashPassword);
		Long userId = service.create(user).getId();
		session.setAttribute("loggedInUser", userId);
		return "redirect:/dashboard";
		
	}
		
	
}
