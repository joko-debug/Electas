package com.electas.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


import com.electas.domain.User;

@Controller
public class NavigationController {

	@GetMapping("/home")
	public String homeView(@AuthenticationPrincipal User user, ModelMap model) {
		return "home";
	}

}
