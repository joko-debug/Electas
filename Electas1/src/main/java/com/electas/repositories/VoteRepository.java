package com.electas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.Ballot;
import com.electas.domain.Candidate;
import com.electas.domain.Vote;


public interface VoteRepository extends JpaRepository<Vote, Long> {

	Set<Vote> findByBallot(Ballot ballot);

	Vote findDistinctByBallotAndPosition(Ballot ballot, int i);

	Vote findDistinctByBallotAndCandidate(Ballot ballot, Candidate candidate);

	Vote getDistinctByBallotAndPosition(Ballot ballot, int i);

}
