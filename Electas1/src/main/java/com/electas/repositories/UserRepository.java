package com.electas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
