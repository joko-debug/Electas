package com.electas.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electas.domain.Ballot;
import com.electas.domain.Candidate;
import com.electas.domain.Election;
import com.electas.domain.User;
import com.electas.repositories.AuthorityRepository;
import com.electas.repositories.BallotRepository;
import com.electas.repositories.CandidateRepository;
import com.electas.repositories.ElectionRepository;
import com.electas.repositories.UserRepository;
import com.electas.sercurity.Authority;


@Service
public class ElectionService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthorityRepository authorityRepo;

	@Autowired
	private ElectionRepository electionRepo;

	@Autowired
	private CandidateRepository candidateRepo;
	
	@Autowired
	private BallotRepository ballotRepo;
	

	public Election createElection(User user, Election elec) {
		user = userRepo.getById(user.getId());
		Set<Authority> auth = authorityRepo.findByUser(user);
		for (Authority authority : auth) {
			if (authority.getAuthority().equals("ROLE_ADMINISTRATOR")) {
				elec.setAdmin(user);
				elec = electionRepo.save(elec);
			}
		}
		return elec;
	}

	// get the set of elections rewired for display
	public Set<Election> getElections(User user) {
		user = userRepo.getById(user.getId());
		Set<Candidate> candidates= candidateRepo.findByUser(user);
		Set<Ballot> ballots=ballotRepo.findByUser(user);
		Set<Election> elections = new HashSet<Election>();
		
		switch (user.getInAs()) {
		case "ROLE_ADMINISTRATOR":
			return electionRepo.findByAdmin(user);
		case "ROLE_CANDIDATE":
			
			for (Iterator iterator = candidates.iterator(); iterator.hasNext();) {
				Candidate candidate = (Candidate) iterator.next();
				elections.add(candidate.getElection());
			}
			return elections;
			
		case "ROLE_VOTER":
			for (Iterator iterator = ballots.iterator(); iterator.hasNext();) {
				Ballot ballot = (Ballot) iterator.next();
				elections.add(ballot.getElection());
			}
			return elections;
		default:
		}
		return null;
	}


	public Candidate createCandidate(User user, Election elec) {
		user = userRepo.getById(user.getId());
		System.out.println(elec.getId());
		Election election = electionRepo.getById(elec.getId());
		Set<Authority> auth = authorityRepo.findByUser(user);
		if(election != null && elec.getName().equalsIgnoreCase(election.getName())) {
		for (Authority authority : auth) {
			if (authority.getAuthority().equals("ROLE_CANDIDATE")) {
			
				Candidate candidate = new Candidate();
				candidate.setElection(elec);
				candidate.setUser(user);
				candidate.setAproved(false);
				return candidateRepo.save(candidate);
				
			}
		}
		}
		return null;
	}

	public Ballot createBallot(User user, Election elec) {
		user = userRepo.getById(user.getId());
		System.out.println(elec.getId());
		Election election = electionRepo.getById(elec.getId());
		Set<Authority> auth = authorityRepo.findByUser(user);
		if(election != null && elec.getName().equalsIgnoreCase(election.getName())) {
		for (Authority authority : auth) {
			if (authority.getAuthority().equals("ROLE_VOTER")) {
			
				Ballot ballot = new Ballot();
				ballot.setElection(elec);
				ballot.setUser(user);
				ballot.setAproved(false);
				return ballotRepo.save(ballot);
				
			}
		}
		}
		return null;
	}
}
