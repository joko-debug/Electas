package com.electas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.electas.domain.AvailableRole;
import com.electas.domain.User;
import com.electas.repositories.AuthorityRepository;
import com.electas.repositories.UserRepository;
import com.electas.sercurity.Authority;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthorityRepository authorityRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// update user
	public User update(User userIN, User change, AvailableRole ar) {
		User user = userRepo.getById(userIN.getId());
		// change password
		System.out.println(user.getPassword());
		if (change.getPassword().equals("") != true) {
			String encodedPassword = passwordEncoder.encode(change.getPassword());
			user.setPassword(encodedPassword);
		}

		// change description
		if ((user.getDescription()+"").equals("") != true) {
			user.setDescription(change.getDescription());
		}

		// change firstName
		if (user.getFirstName().equals("")!= true) {
			user.setFirstName(change.getFirstName());
		}
		// change lastName
		System.out.println(change.getLastName());
		if ((user.getLastName() + "").equals("") != true) {
			user.setLastName(change.getLastName());
			System.out.println(user.getLastName());
		}
		// add voter authritiy

		// change if they are a candidate
		if (ar.getC()) {
			Authority authority = new Authority();
			authority.setAuthority("ROLE_CANDIDATE");
			authority.setUser(user);
			System.out.println(authority.getAuthority()+authority.getUser().getId());
			authorityRepo.save(authority);
		}
		// change if they are a administrator
		Authority authority = new Authority();
		authority.setAuthority("ROLE_ADMINISTRATOR");
		authority.setUser(user);
		if (ar.getA()) {
			authorityRepo.save(authority);
		}// delete authority

		return userRepo.save(user);
	}

//	// permanent deletion of an acount
//	public void nuke(User user) {
//		userRepo.deleteById(user.getId());;
//	}

	// creation of a new account
	public User save(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		Authority authority = new Authority();
		authority.setAuthority("ROLE_VOTER");
		authority.setUser(user);

		user.getAuthorities().add(authority);

		return userRepo.save(user);
	}
	
	public User getUser(User user) {
		user = userRepo.getById(user.getId());
		return user;
	}

}
