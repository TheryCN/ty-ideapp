package com.github.therycn.tyideapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.WorkspaceSave;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.entity.Workspace;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;

public abstract class WorkspaceMapperDecorator implements WorkspaceMapper {

	@Autowired
	@Qualifier("delegate")
	private WorkspaceMapper delegate;

	@Autowired
	private WorkspaceRepository workspaceRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.therycn.tyideapp.mapper.WorkspaceMapper#to(com.github.therycn.
	 * tyideapp.entity.Workspace)
	 */
	@Override
	public WorkspaceListItem to(Workspace workspace) {
		WorkspaceListItem workspaceListItem = delegate.to(workspace);

		if (!CollectionUtils.isEmpty(workspace.getIdeas())) {
			workspaceListItem.setNbIdeas(workspace.getIdeas().size());
		}

		return workspaceListItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.therycn.tyideapp.mapper.WorkspaceMapper#to(com.github.therycn.
	 * tyideapp.WorkspaceSave, com.github.therycn.tyideapp.entity.User)
	 */
	@Override
	public Workspace to(WorkspaceSave workspaceSave, User user) {
		Workspace workspaceUpdate = delegate.to(workspaceSave, user);

		Workspace workspace;
		if (workspaceSave.getId() != null) {
			workspace = workspaceRepo.findById(workspaceSave.getId()).orElse(workspaceUpdate);
			workspace.setName(workspaceUpdate.getName());
		} else {
			workspace = workspaceUpdate;
		}

		return workspace;
	}

}
