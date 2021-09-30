/**
 * 
 */
package com.electas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electas.domain.Election;
import com.electas.domain.User;



/**
 * @author 
 *the election interface to the database
 */
public interface ElectionRepository extends JpaRepository<Election, Long> {

	Set<Election> findByAdmin(User user);

}
