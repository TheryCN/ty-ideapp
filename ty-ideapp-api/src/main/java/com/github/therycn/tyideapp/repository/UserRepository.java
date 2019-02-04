package com.github.therycn.tyideapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.therycn.tyideapp.entity.User;

/**
 * User Repository.
 * 
 * @author tcharass
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
