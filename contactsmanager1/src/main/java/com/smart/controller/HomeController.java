package com.smart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;


@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
		public String home(Model  model) {
			model.addAttribute("title", "Home - Smart Contact Manager");
					return "home";
		}
	
	
	@RequestMapping("/about")
	public String about(Model  model) {
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}
	
	
		
		@RequestMapping("/signup")
		public String signup(Model  model) {
			model.addAttribute("title", "Register - Smart Contact Manager");
			model.addAttribute("user",new User());
				return "signup";
		}
	
		//handler for registering user
		@RequestMapping(value="/do_register",method = RequestMethod.POST)
		public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="argeement",defaultValue="false") boolean argeement,Model model) {
			if(!argeement) {
				System.out.println("You have not agreed terms and condition");
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			System.out.println("Argeement"+argeement);
			System.out.println("User"+user);
			User result=this.userRepository.save(user);
		
			model.addAttribute("user",result);
			return "signup";
		}
		

}
