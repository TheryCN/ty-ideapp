package com.github.therycn.tyideapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.mapper.WorkspaceMapper;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;

import lombok.AllArgsConstructor;

/**
 * Workspace Rest Controller.
 * 
 * @author THERY
 *
 */
@RestController
@RequestMapping("/workspace")
@AllArgsConstructor
public class WorkspaceRestController {

	private WorkspaceMapper workspaceMapper;

	private WorkspaceRepository workspaceRepo;

	@GetMapping("/")
	public List<WorkspaceListItem> list(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return workspaceRepo.findByUserIdOrderByName(user.getId()).stream().map(workspaceMapper::to)
				.collect(Collectors.toList());
	}
}
