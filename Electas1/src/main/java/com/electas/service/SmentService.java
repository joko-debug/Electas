package com.electas.service;

import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electas.domain.Election;
import com.electas.domain.Sment;
import com.electas.domain.User;
import com.electas.repositories.ElectionRepository;
import com.electas.repositories.SmentRepository;
import com.electas.repositories.UserRepository;

@Service
public class SmentService {
	@Autowired
	private SmentRepository smentRepo;

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ElectionRepository electionRepo;

	public void makeSment(User user, Sment sment) {

		user = userRepo.getById(user.getId());
		Date date = new Date(System.currentTimeMillis());
		sment.setCreated(date);
		sment.setUser(user);
		if (sment.getPost().equals("") == false) {
			smentRepo.save(sment);
		}
	}

	public Set<Sment> getUserSment(User user) {
		user =userRepo.getById(user.getId());
		Set<Sment> sments = smentRepo.findByUser(user);
		return sments;

	}

	public void makeElectionSment(User user, Sment sment) {
		Election election= electionRepo.getById(sment.getElection().getId());
		user =userRepo.getById(user.getId());
		Date date = new Date(System.currentTimeMillis());
		System.out.println("Election id"+election.getId());
		election = electionRepo.getById(election.getId());
		sment.setCreated(date);
		sment.setUser(user);
		sment.setElection(election);
		if (sment.getPost().equals("") == false) {
			smentRepo.save(sment);
		}
		// TODO Auto-generated method stub
		
	}

	public Set<Sment> getELectionSment(Election election) {
		Set<Sment> sments = smentRepo.findByElection(election);
		return sments;
	}

	

	public void makeElectionSment(User user, Election election, Sment sment) {
		election= electionRepo.getById(election.getId());
		user =userRepo.getById(user.getId());
		Date date = new Date(System.currentTimeMillis());
		System.out.println("Election id"+election.getId());
		election = electionRepo.getById(election.getId());
		sment.setCreated(date);
		sment.setUser(user);
		sment.setElection(election);
		if (sment.getPost().equals("") == false) {
			smentRepo.save(sment);
		}
		
	}

	public void makeHelpSment(User user, Sment sment) {
		Election election = electionRepo.getById((long) 3);
		user =userRepo.getById(user.getId());
		Date date = new Date(System.currentTimeMillis());
		System.out.println("Election id"+election.getId());
		election = electionRepo.getById(election.getId());
		sment.setCreated(date);
		sment.setUser(user);
		sment.setElection(election);
		if (sment.getPost().equals("") == false) {
			smentRepo.save(sment);
		}
		
	}

}
