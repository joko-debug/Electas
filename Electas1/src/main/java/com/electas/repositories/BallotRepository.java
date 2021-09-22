package com.electas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.Ballot;
import com.electas.domain.User;


public interface BallotRepository extends JpaRepository<Ballot, Long> {

	Set<Ballot> findByUser(User user);

}
