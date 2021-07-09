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

@Entity
public class Sment {
//sment short for statement
	private Long id;
	private User user;
	private String post;
	private Date created;
	private Date updated;
	private Sment resment;
	private Sment reply_to;
	private Election election;
	private Set<Sment> reSment = new HashSet<>();
	private Set<Sment> reply_To = new HashSet<>();
	private Set<UpDown> updown = new HashSet<>();


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


	public String getPost() {
		return post;
	}

	
	public void setPost(String post) {
		this.post = post;
	}


	public Date getCreated() {
		return created;
	}

	
	public void setCreated(Date created) {
		this.created = created;
	}

	
	public Date getUpdated() {
		return updated;
	}

	
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	
	@ManyToOne
	public Sment getResment() {
		return resment;
	}

	
	public void setResment(Sment resment) {
		this.resment = resment;
	}

	
	@ManyToOne
	public Sment getReply_to() {
		return reply_to;
	}

	
	public void setReply_to(Sment reply_to) {
		this.reply_to = reply_to;
	}

	
	@ManyToOne
	public Election getElection() {
		return election;
	}

	
	@ManyToOne
	public void setElection(Election election) {
		this.election = election;
	}

	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "resment")
	public Set<Sment> getReSment() {
		return reSment;
	}

	
	public void setReSment(Set<Sment> reSment) {
		this.reSment = reSment;
	}

	
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "reply_to")
	public Set<Sment> getReply_To() {
		return reply_To;
	}

	
	public void setReply_To(Set<Sment> reply_To) {
		this.reply_To = reply_To;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "sment")
	public Set<UpDown> getUpdown() {
		return updown;
	}


	public void setUpdown(Set<UpDown> updown) {
		this.updown = updown;
	}

}
