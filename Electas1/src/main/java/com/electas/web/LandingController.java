package com.electas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.electas.domain.User;
import com.electas.service.UserService;




@Controller
public class LandingController {
	@Autowired
	  private UserService userService;

	 @GetMapping("/landing")
	  public String rootView (ModelMap model) {
		 model.put("user", new User());
	    return "landing_page";
	  }
	 
	 @PostMapping("/landing_register")
	  public String registerPost (User user) {
	    User saved = userService.save(user);
	    
	    return "redirect:/home";
	  }
}
