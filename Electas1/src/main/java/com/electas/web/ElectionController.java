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
import com.electas.domain.Sment;
import com.electas.domain.User;
import com.electas.domain.Vote;
import com.electas.service.ElectionService;
import com.electas.service.SmentService;
import com.electas.service.UserService;

@Controller
public class ElectionController {

	@Autowired
	ElectionService electionService;
	@Autowired
	UserService userService;
	@Autowired
	SmentService smentService;

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
	public String viewElection(@PathVariable("electionId") long electionId, @AuthenticationPrincipal User user,
			ModelMap model) {
		user = userService.getUser(user);
		Election election = electionService.getElections(electionId);
		Set<Candidate> allCandidates = electionService.getAllCandidates(election);
		Set<Candidate> resultCandidates = electionService.getRankedList(election);
		Set<Sment> sments= smentService.getELectionSment(election);
		Set<Vote> votes = electionService.getVoterVotes(election,user);
		int numB = electionService.getBallotCount(election);
		int numC = electionService.getElectionCount(election);
		Sment s = new Sment();
		s.setElection(election);
		model.put("newSment", s);
		model.put("sments",sments);
		model.put("votes", votes);
		model.put("election", election);
		model.put("allCandidates", allCandidates);
		model.put("results", resultCandidates);
		model.put("numB", numB);
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

	@RequestMapping(method = RequestMethod.GET, path = "/vote/addVote/{candidateId}")
	public String addVote(@PathVariable("candidateId") long candidateId, @AuthenticationPrincipal User user,
			ModelMap model) {
		Election e = electionService.addVote(candidateId, user);
		return "redirect:/viewElection/" + e.getId();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/vote/removeVote/{voteId}")
	public String removeVote(@PathVariable("voteId") long voteId, @AuthenticationPrincipal User user,
			ModelMap model) {
		Election e = electionService.removeVote(voteId, user);
		return "redirect:/viewElection/" + e.getId();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/vote/moveVote/{voteId}/{g}")
	public String moveVote(@PathVariable("voteId") long voteId,@PathVariable("g")String move, @AuthenticationPrincipal User user,
			ModelMap model) {
		Election e = electionService.moveVote(voteId, user,move);
		return "redirect:/viewElection/" + e.getId();
	}
}
