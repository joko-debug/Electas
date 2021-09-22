package com.electas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electas.domain.Ballot;
import com.electas.domain.Candidate;
import com.electas.domain.Election;
import com.electas.domain.User;
import com.electas.service.ElectionService;

@Controller
public class ElectionController {
	
	@Autowired
	ElectionService electionService;
	
	@RequestMapping(value = "/makeElection", method = RequestMethod.POST)
	public String createElelction(@AuthenticationPrincipal User user,@ModelAttribute("election") final Election election,
	  final BindingResult result, final ModelMap model) {
		Election electio = electionService.createElection(user,election);
		return "redirect:/nav";
	}
	
	
	@RequestMapping(value = "/makeCandidate", method = RequestMethod.POST)
	public String createCandidate(@AuthenticationPrincipal User user,@ModelAttribute("election") final Election election,
	  final BindingResult result, final ModelMap model) {
		Candidate candi = electionService.createCandidate(user,election);
		return "redirect:/nav";
	}
	
	@RequestMapping(value = "/makeBallot", method = RequestMethod.POST)
	public String createBallot(@AuthenticationPrincipal User user,@ModelAttribute("election") final Election election,
	  final BindingResult result, final ModelMap model) {
		Ballot ballot = electionService.createBallot(user,election);
		return "redirect:/nav";
	}
}
