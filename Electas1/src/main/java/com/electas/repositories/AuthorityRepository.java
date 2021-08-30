package com.electas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.User;
import com.electas.sercurity.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Set<Authority> findByUser(User user);
}
 