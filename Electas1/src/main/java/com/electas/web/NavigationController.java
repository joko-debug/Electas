package com.electas.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.electas.domain.AvailableRole;
import com.electas.domain.User;
import com.electas.service.UserService;

@Controller
public class NavigationController {
	@Autowired
	UserService userService;
	
	@GetMapping("/nav")
	public String homeView(@AuthenticationPrincipal User user, ModelMap model) {
		return "nav";
	}

	@GetMapping("/nav/home")
	public String navhome() {
		// ModelAndView mv= new ModelAndView("search::search_list");
		return "fragments/navigation::home";
	}

	@GetMapping("/nav/election")
	public String navelection() {
		// ModelAndView mv= new ModelAndView("search::search_list");
		return "fragments/navigation::election";
	}

	@GetMapping("/nav/profile")
	public String navprofile(@AuthenticationPrincipal User user, ModelMap model) {
		
		model.addAttribute("user", userService.getUser(user));
		AvailableRole ar = new AvailableRole(false, false);
		model.addAttribute("ar",ar);
		return "fragments/navigation::profile";
	}

	@GetMapping("/nav/help")
	public String navhelp() {
		// ModelAndView mv= new ModelAndView("search::search_list");
		return "fragments/navigation::help";
	}


}
