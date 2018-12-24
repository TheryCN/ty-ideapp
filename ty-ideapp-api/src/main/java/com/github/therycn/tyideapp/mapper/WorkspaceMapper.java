package com.github.therycn.tyideapp.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.entity.Workspace;

/**
 * Workspace Mapper.
 * 
 * @author TheryLeopard
 *
 */
@Mapper(componentModel = "spring")
@DecoratedWith(WorkspaceMapperDecorator.class)
public interface WorkspaceMapper {

	WorkspaceListItem to(Workspace workspace);

}
