package com.electas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.Vote;


public interface VoteRepository extends JpaRepository<Vote, Long> {

}
