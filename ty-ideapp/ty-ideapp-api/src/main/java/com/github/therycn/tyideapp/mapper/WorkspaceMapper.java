package com.github.therycn.tyideapp.mapper;

import com.github.therycn.tyideapp.WorkspaceListItem;
import com.github.therycn.tyideapp.WorkspaceSave;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.entity.Workspace;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Workspace Mapper.
 */
@Mapper(componentModel = "spring")
@DecoratedWith(WorkspaceMapperDecorator.class)
public interface WorkspaceMapper {

    WorkspaceListItem to(Workspace workspace);

    @Mappings({@Mapping(source = "workspace.id", target = "id"), @Mapping(source = "user.id", target = "user.id")})
    Workspace to(WorkspaceSave workspace, User user);

}
