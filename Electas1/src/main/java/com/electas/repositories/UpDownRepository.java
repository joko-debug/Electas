package com.electas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.UpDown;

public interface UpDownRepository extends JpaRepository<UpDown, Long> {
	
}
