package com.github.therycn.tyideapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.WorkspaceListItem;
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
@CrossOrigin
@AllArgsConstructor
public class WorkspaceRestController {

	private WorkspaceMapper workspaceMapper;

	private WorkspaceRepository workspaceRepo;

	@GetMapping("/")
	public List<WorkspaceListItem> list() {
		return workspaceRepo.findAll().stream().map(workspaceMapper::to).collect(Collectors.toList());
	}
}
