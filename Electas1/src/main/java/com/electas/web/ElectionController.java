package com.electas.web;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.electas.domain.Ballot;
import com.electas.domain.Candidate;
import com.electas.domain.Election;
import com.electas.domain.User;
import com.electas.service.ElectionService;
import com.electas.service.UserService;

@Controller
public class ElectionController {

	@Autowired
	ElectionService electionService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/makeElection", method = RequestMethod.POST)
	public String createElelction(@AuthenticationPrincipal User user,
			@Valid @ModelAttribute("election") final Election election, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "redirect:/nav";
		}
		Election electio = electionService.createElection(user, election);
		return "redirect:/nav";
	}

	@RequestMapping(value = "/makeCandidate", method = RequestMethod.POST)
	public String createCandidate(@AuthenticationPrincipal User user,
			@Valid @ModelAttribute("election") final Election election, final BindingResult result, ModelMap model) {
		Candidate candi = electionService.createCandidate(user, election);
		return "redirect:/nav";
	}

	@RequestMapping(value = "/makeBallot", method = RequestMethod.POST)
	public String createBallot(@AuthenticationPrincipal User user, @ModelAttribute("election") final Election election,
			final BindingResult result, final ModelMap model) {
		Ballot ballot = electionService.createBallot(user, election);
		return "redirect:/nav";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/viewElection/{electionId}")
	public String viewElection(@PathVariable("electionId") long electionId, @AuthenticationPrincipal User user,ModelMap model) {
		user = userService.getUser(user);
		Election election = electionService.getElections(electionId);
		Set<Candidate> allCandidates = electionService.getAllCandidates(election);
		Set<Candidate> resultCandidates = electionService.getRankedList(election);
		int numB = electionService.getBallotCount(election);
		int numC = electionService.getElectionCount(election);
		model.put("election", election);
		model.put("allCandidates",allCandidates);
		model.put("results", resultCandidates);
		model.put("numB",numB);
		model.put("numC", numC);
		System.out.println(user.getInAs());
		
		switch (user.getInAs()) {
		case "ROLE_ADMINISTRATOR":
			return "fragments/elections::adminfrag";
		case "ROLE_CANDIDATE":
			return "fragments/elections::candidatefrag";
		default:
			return "fragments/elections::voterfrag";
		}

	}

}
