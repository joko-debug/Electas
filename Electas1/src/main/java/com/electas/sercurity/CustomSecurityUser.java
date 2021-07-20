package com.electas.sercurity;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import com.electas.domain.User;

public class CustomSecurityUser extends User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5265548424642402487L;

	public CustomSecurityUser() {
	}

	public CustomSecurityUser(User user) {
		this.setAuthorities(user.getAuthorities());
		this.setId(user.getId());
		this.setFirstName(user.getFirstName());
		this.setPassword(user.getPassword());
		this.setUsername(user.getUsername());
	}

	@Override
	public Set<Authority> getAuthorities() {
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
