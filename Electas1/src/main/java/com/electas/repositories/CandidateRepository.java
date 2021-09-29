package com.electas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.Candidate;
import com.electas.domain.Election;
import com.electas.domain.User;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	Candidate getByUser(User user);

	Set<Candidate> findByUser(User user);

	Set<Candidate> findByElection(Election election);


}
