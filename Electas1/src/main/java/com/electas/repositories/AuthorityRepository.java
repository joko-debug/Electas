package com.electas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.User;
import com.electas.sercurity.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	Authority findByUser(User user);
}
 