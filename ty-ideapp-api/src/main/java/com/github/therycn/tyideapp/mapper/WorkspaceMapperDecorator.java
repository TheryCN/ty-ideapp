package com.github.therycn.tyideapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.entity.Workspace;

public abstract class WorkspaceMapperDecorator implements WorkspaceMapper {

	@Autowired
	@Qualifier("delegate")
	private WorkspaceMapper delegate;

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

}
