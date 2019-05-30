package com.github.therycn.tyideapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.github.therycn.tyideapp.entity.Idea;

/**
 * Idea Repository (REST is available at /ideas).
 * 
 * @author TheryLeopard
 *
 */
@CrossOrigin
public interface IdeaRepository extends JpaRepository<Idea, Long> {

}
