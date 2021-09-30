package com.electas.domain;

import java.sql.Date;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Election {

	//table containing election information
	
	private Long id;
	private User admin;
	@NotEmpty(message = "please enter")
	private String name;
	@NotEmpty(message = "please enter")
	private String description;
	@Min(1)
	private Integer winnerCount;
	private Date startDate;
	private Date endDate;
	private Set<Candidate> candidates = new HashSet<>();
	private Set<Sment> sment = new HashSet<>();
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne()
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getWinnerCount() {
		return winnerCount;
	}
	public void setWinnerCount(Integer winnerCount) {
		this.winnerCount = winnerCount;
	}
	
	//date at the start of the election
	public Date getStartDate() {
		return startDate;
	}
	
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	//date at the end of the election 
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Election [id=" + id + ", admin=" + admin + ", description=" + description + ", winnerCount="
				+ winnerCount + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	/**
	 * @return the sment
	 */
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="election")
	public Set<Sment> getSment() {
		return sment;
	}

	/**
	 * @param sment the sment to set
	 */
	public void setSment(Set<Sment> sment) {
		this.sment = sment;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
	
}
