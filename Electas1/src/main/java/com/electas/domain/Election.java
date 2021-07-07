package com.electas.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Election {

	//table containing election information
	
	private Long id;
	private User admin;
	private String description;
	private Integer winnerCount;
	private Date startDate;
	private Date endDate;
	
	
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
	
	
}
