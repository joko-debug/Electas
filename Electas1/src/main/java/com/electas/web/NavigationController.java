package com.electas.web;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electas.domain.AvailableRole;
import com.electas.domain.Election;
import com.electas.domain.User;
import com.electas.service.ElectionService;
import com.electas.service.UserService;

@Controller
public class NavigationController {
	@Autowired
	UserService userService;
	@Autowired
	ElectionService electionService;

	@GetMapping("/nav")
	public String homeView(@AuthenticationPrincipal User auser, ModelMap model) {
		User user = userService.getUser(auser);
		user.setPassword("");
		model.addAttribute("mainUser", user);
		model.put("election", new Election());
		return "nav";
	}

	// different pages
	@GetMapping("/nav/home")
	public String navhome(@AuthenticationPrincipal User user, ModelMap model) {
		Set<Election> elections =electionService.getElections(user);
		model.put("elections",elections);
		return "fragments/navigation::home";
	}

	@GetMapping("/nav/election")
	public String navelection(@AuthenticationPrincipal User user, ModelMap model) {
		Set<Election> elections =electionService.getElections(user);
		model.put("elections",elections);
		return "fragments/navigation::election";
	}

	@GetMapping("/nav/profile")
	public String navprofile(@AuthenticationPrincipal User user, ModelMap model) {

		model.addAttribute("user", userService.getUser(user));
		AvailableRole ar = new AvailableRole(false, false);
		model.addAttribute("ar", ar);
		return "fragments/navigation::profile";
	}

	@GetMapping("/nav/help")
	public String navhelp() {
		return "fragments/navigation::help";
	}

	// changes in as Value
	@RequestMapping(method = RequestMethod.GET, path = "/inAs/{inas}")
	public String navhome(@PathVariable("inas") String inas,@AuthenticationPrincipal User user) {
		userService.changeInAs(inas,user);
		return "redirect:/nav";
	}

}
