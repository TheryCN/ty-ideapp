package com.github.therycn.tyideapp.controller;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.WorkspaceSave;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.mapper.WorkspaceMapper;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Workspace Rest Controller.
 */
@RestController
@RequestMapping("/workspaces")
public class WorkspaceRestController {

    private final WorkspaceMapper workspaceMapper;

    private final WorkspaceRepository workspaceRepository;

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
