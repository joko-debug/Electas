package com.electas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electas.domain.AvailableRole;
import com.electas.domain.User;
import com.electas.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	  private UserService userService;

	//when update button is pressed
	@RequestMapping(value = "profileChange", method = RequestMethod.POST, params = "update")// updates the users profile
	public String update(@AuthenticationPrincipal User user,@ModelAttribute("user") final User change,@ModelAttribute("ar") final AvailableRole ar,
	  final BindingResult result, final ModelMap model) {
		User saved = userService.update(user,change, ar);
		return "redirect:/nav";
	}
//	//when nuke button is pressed
//	@RequestMapping(value = "profileChange", method = RequestMethod.POST, params = "nuke")
//	public String nuke( @AuthenticationPrincipal User user, ModelMap model) {
//	   userService.nuke(user);
//	    return "redirect:/logout";
//	}
	
}
