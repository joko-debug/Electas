package com.electas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.Election;
import com.electas.domain.Sment;
import com.electas.domain.User;



public interface SmentRepository extends JpaRepository<Sment, Long> {

	Set<Sment> findByUser(User user);

	Set<Sment> findByElection(Election election);

}
