package com.electas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UpDown {

	private Boolean updown;
	private Sment sment;
	private User user;
	private Long id;
	
	
	 
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	public  Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean getUpdown() {
		return updown;
	}
	public void setUpdown(Boolean updown) {
		this.updown = updown;
	}
	
	@ManyToOne
	public Sment getSment() {
		return sment;
	}
	public void setSment(Sment sment) {
		this.sment = sment;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
