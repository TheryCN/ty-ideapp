package com.github.therycn.tyideapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.WorkspaceSave;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.mapper.WorkspaceMapper;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;

/**
 * Workspace Rest Controller.
 * 
 * @author THERY
 *
 */
@RestController
@RequestMapping("/workspaces")
public class WorkspaceRestController {

	private WorkspaceMapper workspaceMapper;

	private WorkspaceRepository workspaceRepository;

	public WorkspaceRestController(WorkspaceMapper workspaceMapper, WorkspaceRepository workspaceRepository) {
		this.workspaceMapper = workspaceMapper;
		this.workspaceRepository = workspaceRepository;
	}

	@GetMapping("/")
	public List<WorkspaceListItem> list(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return workspaceRepository.findByUserIdOrderByName(user.getId()).stream().map(workspaceMapper::to)
				.collect(Collectors.toList());
	}

	@PostMapping("/")
	public WorkspaceListItem save(@Valid @RequestBody WorkspaceSave workspace, Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return workspaceMapper.to(workspaceRepository.save(workspaceMapper.to(workspace, user)));
	}
}
