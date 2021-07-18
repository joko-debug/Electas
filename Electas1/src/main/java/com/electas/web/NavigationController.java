package com.electas.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.electas.domain.User;

@Controller
@RequestMapping
public class NavigationController {

	@GetMapping("/home")
	public String rootView() {
		return "home";
	}

}
