package com.github.therycn.tyideapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.mapper.IdeaMapper;
import com.github.therycn.tyideapp.repository.IdeaRepository;

import lombok.AllArgsConstructor;

/**
 * Idea Rest Controller.
 * 
 * @author TheryLeopard
 *
 */
@RestController
@RequestMapping("/idea")
@CrossOrigin
@AllArgsConstructor
public class IdeaRestController {

	private IdeaRepository ideaRepo;

	private IdeaMapper ideaMapper;

	@PostMapping("/")
	public IdeaSave save(@RequestBody IdeaSave idea) {
		return ideaMapper.to(ideaRepo.save(ideaMapper.to(idea)));
	}

}
