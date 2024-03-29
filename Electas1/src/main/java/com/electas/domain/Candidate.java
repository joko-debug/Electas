package com.electas.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Candidate {
	// table containing all the elections only editable by election administrators.

	private Long id;
	private User user;
	private Election election;
	private Boolean aproved;
	private String status;
	private Double score = 0.0;
	private Set<Vote> votes = new HashSet<>();

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "candidate")
	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	public Boolean getAproved() {
		return aproved;
	}

	public void setAproved(Boolean aproved) {
		this.aproved = aproved;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", user=" + user + ", election=" + election + ", aproved=" + aproved + "]";
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		if (score == null)
			return 0.0;
		else
			return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		if (status == null)
			return "";
		else
			return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
