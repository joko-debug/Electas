package com.electas.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import com.electas.custom_anotations.IdFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class User {

	private Long id;
	private String username;
	@NotEmpty(message = "please enter password")
	private String password;
	@NotEmpty(message = "please enter firstName")
	private String firstName;
	@NotEmpty(message = "please enter lastName")
	private String lastName;
	
	@NotEmpty
	@Email(message = "invalid email adress")
	private String email;
	private String inAs;//to specify what type user they are currently in as;
	private String description;
	
	@NotEmpty
	@Size(min = 13,max=13, message = "id must have 13 charaters")
	@IdFormat
	private String idNumber;
	private Set<Authority> authorities = new HashSet<>();
	private Set<Candidate> candidates = new HashSet<>();
	private Set<Election> elections = new HashSet<>();
	private Set<Ballot> ballots = new HashSet<>();
	private Set<Sment> sments = new HashSet<>();
	private Set<UpDown> updown = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "admin")
	public Set<Election> getElections() {
		return elections;
	}

	public void setElections(Set<Election> elections) {
		this.elections = elections;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Ballot> getBallots() {
		return ballots;
	}

	public void setBallots(Set<Ballot> ballots) {
		this.ballots = ballots;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", description=" + description + ", idNumber="
				+ idNumber + ", authorities=" + authorities + ", candidates=" + candidates + ", elections=" + elections
				+ ", ballots=" + ballots + "]";
	}

	/**
	 * @return the sments
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Sment> getSments() {
		return sments;
	}

	/**
	 * @param sments the sments to set
	 */
	public void setSments(Set<Sment> sments) {
		this.sments = sments;
	}

	/**
	 * @return the updown
	 */
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UpDown> getUpdown() {
		return updown;
	}

	/**
	 * @param updown the updown to set
	 */
	public void setUpdown(Set<UpDown> updown) {
		this.updown = updown;
	}

	
	/**
	 * @return the inAs
	 */
	public String getInAs() {
		return inAs;
	}

	/**
	 * @param inAs the inAs to set
	 */
	public void setInAs(String inAs) {
		this.inAs = inAs;
	}

}
