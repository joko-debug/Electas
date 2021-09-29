package com.electas.customCode;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.electas.domain.Ballot;
import com.electas.domain.Candidate;
import com.electas.domain.Election;
import com.electas.domain.Vote;
import com.electas.repositories.VoteRepository;

@Service
public class STV {
	@Autowired
	VoteRepository voteRepo;

	private Set<Ballot> ballots;
	private Set<Candidate> candidates;
	private Election election;
	double quota;

	public Set<Candidate> getElectionResults(Set<Ballot> ballots, Set<Candidate> candidates, Election election) {
		this.ballots = ballots;
		this.candidates = candidates;
		this.election = election;

		if (ballots.isEmpty()) {
			return null;
		}
		if (candidates.isEmpty()) {
			return null;
		}
		Long sumVotes= (long) 0;
		for (Ballot ballot : ballots) {
			sumVotes +=voteRepo.findByBallot(ballot).size();
		}

		initiateScore();
		quota = ballots.size() / (election.getWinnerCount() + 1) + 1;
		int sumElected = 0;
		do {
			for (Iterator iterator = candidates.iterator(); iterator.hasNext();) {
				Candidate candidate = (Candidate) iterator.next();
				if (candidate.getScore() >= quota) {
					candidates.remove(candidate);
					candidate.setStatus("elected");
					candidates.add(candidate);
					relocatedWinnerVotes(candidate);
					sumElected++;
				} else {
					Candidate lowest = getLowestScore();
					relocateVotes(lowest);
					boolean l = candidates.remove(lowest);
					if (l) {
						lowest.setStatus("defeated");
						candidates.add(lowest);
					}
				}
				if (sumElected != ballots.size()) {
					break;
				}
			}
		} while (sumElected != election.getWinnerCount() && sumVotes>2);
		return candidates;
	}

	private void relocateVotes(Candidate lowest) {
		for (Ballot ballot : ballots) {
			int count = 1;
			// look for ballot with the looser at the top
			Vote vote1 = voteRepo.findDistinctByBallotAndPosition(ballot, count);
			if (vote1 != null) {
				System.out.println("empty stuff");

				while (vote1.getCandidate().getStatus().equals("") != true) {
					vote1 = voteRepo.findDistinctByBallotAndPosition(ballot, vote1.getPosition() + count);
					count++;
				}
				if (vote1.getCandidate().equals(lowest)) {
					// find the next viable candidate
					int count1 = vote1.getPosition();
					Vote vote2 = voteRepo.findDistinctByBallotAndPosition(ballot, count + 1);
					while (vote2.getCandidate().getStatus().equals("") != true) {
						vote2 = voteRepo.findDistinctByBallotAndPosition(ballot, vote2.getPosition() + count);
						count++;
					}

					// give votes to next vialbe candidate
					for (Candidate candidate : candidates) {
						if (vote2.getCandidate().equals(candidate)) {
							candidates.remove(candidate);
							candidate.setScore(candidate.getScore() + ballot.getWaight());
							candidates.add(candidate);
						}
					}
				}
			}
		}

	}

	private Candidate getLowestScore() {
		double lowest = 1000000000;
		Candidate low = null;
		for (Candidate candidate : candidates) {
			if (candidate.getScore() < lowest && candidate.getStatus().equals("")) {
				low = candidate;
				lowest = low.getScore();
			}
		}
		return low;
	}

	private void relocatedWinnerVotes(Candidate candidate) {
		double excess = candidate.getScore() - quota;
		for (Iterator iterator = ballots.iterator(); iterator.hasNext();) {
			Ballot ballot = (Ballot) iterator.next();
			Vote vote = voteRepo.findDistinctByBallotAndCandidate(ballot, candidate);
			// if the top vote of the ballot is the candidate;
			if (vote.getCandidate() == candidate) {
				ballots.remove(ballot);
				ballot.setWaight(ballot.getWaight() * (excess / candidate.getScore()));
				ballots.add(ballot);
				// get vote in next posistion on the ballot
				int count = 1;
				Vote vote1 = voteRepo.findDistinctByBallotAndPosition(ballot, vote.getPosition() + 1);

				while (vote1.getCandidate().getStatus().equals("") != true) {
					vote1 = voteRepo.findDistinctByBallotAndPosition(ballot, vote.getPosition() + count);
					count++;
				}
				if (vote1.getCandidate() != null && vote1.getCandidate().getStatus().equals("")) {
					// edit next vote of a ballot
					candidate = vote1.getCandidate();
					candidates.remove(candidate);
					candidate.setScore(candidate.getScore() + ballot.getWaight());
					candidates.add(candidate);
				}
			}
		}

	}

	private void initiateScore() {

		for (Iterator i = ballots.iterator(); i.hasNext();) {
			// interate all the votes
			Ballot ballot = (Ballot) i.next();
			System.out.println(ballot.toString());
			// get the first vote
			Set<Vote> votes = voteRepo.findByBallot(ballot);
			Vote vote = null;
			if (votes.isEmpty() != true) {
				for (Vote votei : votes) {
					if (votei.getPosition() == 1) {
						vote = votei;
					}
				}
			}
			vote = voteRepo.findDistinctByBallotAndPosition(ballot, 1);
			if (vote != null) {

				for (Iterator j = candidates.iterator(); j.hasNext();) {

					Candidate candidate = (Candidate) j.next();
					// find candidate with vote
					if (vote.getCandidate() == candidate) {
						// edit first vote of a ballot
						candidates.remove(candidate);
						candidate.setScore(candidate.getScore() + 1);
						candidates.add(candidate);
					}
				}
			}

		}

	}

}
