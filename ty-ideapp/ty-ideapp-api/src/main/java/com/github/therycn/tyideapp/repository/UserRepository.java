package com.github.therycn.tyideapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.therycn.tyideapp.entity.User;

/**
 * User Repository.
 * 
 * @author tcharass
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User set password = :newPassword where id = :id")
    int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

}
