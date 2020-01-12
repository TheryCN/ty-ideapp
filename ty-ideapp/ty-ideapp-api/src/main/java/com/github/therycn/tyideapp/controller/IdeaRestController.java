package com.github.therycn.tyideapp.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.mapper.IdeaMapper;
import com.github.therycn.tyideapp.repository.IdeaRepository;

/**
 * Idea Rest Controller.
 * 
 * @author TheryLeopard
 *
 */
@RestController
@RequestMapping("/ideas")
@CrossOrigin
public class IdeaRestController {

	private IdeaRepository ideaRepo;

	private IdeaMapper ideaMapper;

	public IdeaRestController(IdeaRepository ideaRepo, IdeaMapper ideaMapper) {
		this.ideaRepo = ideaRepo;
		this.ideaMapper = ideaMapper;
	}

	@PostMapping("/")
	public IdeaSave save(@Valid @RequestBody IdeaSave idea) {
		return ideaMapper.to(ideaRepo.save(ideaMapper.to(idea)));
	}

}
