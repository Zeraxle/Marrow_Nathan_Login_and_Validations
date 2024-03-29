package com.nathanm.loginandvalidation.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nathanm.loginandvalidation.models.LoginUser;
import com.nathanm.loginandvalidation.models.User;
import com.nathanm.loginandvalidation.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired 
	private UserService service;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser loginUser, 
						BindingResult result, Model model) {
		
		User potentialUser = service.get(loginUser.getEmail());
		if (potentialUser == null) {
			result.rejectValue("email", "invalid user", "Invalid login");
		}
		
		else {
			if (!BCrypt.checkpw(loginUser.getPassword(), potentialUser.getPassword())) {
				result.rejectValue("email", "invalid user", "Invalid login");
			}
		}
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		}
		session.setAttribute("loggedInUser", potentialUser.getId());
		System.out.println(session.getAttribute("loggedInUser"));
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String homePage(RedirectAttributes redirect, Model model) {
		if (session.getAttribute("loggedInUser") == null) {
			redirect.addFlashAttribute("notLoggedIn", "You must be logged in to view that page");
			return "redirect:/";
		}
		User user = service.get((Long) session.getAttribute("loggedInUser"));
		model.addAttribute("user", user);
		return "dashboard.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
