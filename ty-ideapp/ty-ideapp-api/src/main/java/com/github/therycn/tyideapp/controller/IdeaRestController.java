package com.github.therycn.tyideapp.controller;

import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.mapper.IdeaMapper;
import com.github.therycn.tyideapp.repository.IdeaRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Idea Rest Controller.
 */
@RestController
@RequestMapping("/ideas")
@CrossOrigin
public class IdeaRestController {

    private final IdeaRepository ideaRepo;

    private final IdeaMapper ideaMapper;

    public IdeaRestController(IdeaRepository ideaRepo, IdeaMapper ideaMapper) {
        this.ideaRepo = ideaRepo;
        this.ideaMapper = ideaMapper;
    }

    @PostMapping("/")
    public IdeaSave save(@Valid @RequestBody IdeaSave idea) {
        return ideaMapper.to(ideaRepo.save(ideaMapper.to(idea)));
    }

}
