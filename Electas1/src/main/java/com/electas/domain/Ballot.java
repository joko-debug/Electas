package com.electas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ballot {

	private Long id;
	@Override
	public String toString() {
		return "Ballot [id=" + id + ", election=" + election + ", user=" + user + ", aproved=" + aproved + ", waight="
				+ waight + "]";
	}
	private Election election;
	private User user;
	private boolean aproved;
	private double  waight;
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getWaight() {
		return waight;
	}
	public void setWaight(double waight) {
		if(waight > 1)
			waight = 1;
		this.waight = waight;
	}
	
	@ManyToOne
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isAproved() {
		return aproved;
	}
	public void setAproved(boolean aproved) {
		this.aproved = aproved;
	}
	
	
}
