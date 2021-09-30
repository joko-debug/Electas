package com.electas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.electas.domain.Election;
import com.electas.domain.Sment;
import com.electas.domain.User;
import com.electas.service.SmentService;

@Controller
public class SmentController {
	@Autowired
	SmentService smentService;

	@PostMapping("/sment/makeSment")
	public String makeSment(@AuthenticationPrincipal User user, @ModelAttribute("newSment") Sment sment, Model model) {
		smentService.makeSment(user, sment);
		return "redirect:/nav";
	}

	@PostMapping("/sment/makeElectionSment")
	public String makeElectionSment(@AuthenticationPrincipal User user, @ModelAttribute("newSment") Sment sment,
			@ModelAttribute("election") Election election, Model model) {
		smentService.makeElectionSment(user, election, sment);
		return "redirect:/nav";
	}
	
	@PostMapping("/sment/makeHelpSment")
	public String makeHeloSment(@AuthenticationPrincipal User user, @ModelAttribute("newSment") Sment sment,
			 Model model) {
		smentService.makeHelpSment(user,sment);
		return "redirect:/nav";
	}
}
