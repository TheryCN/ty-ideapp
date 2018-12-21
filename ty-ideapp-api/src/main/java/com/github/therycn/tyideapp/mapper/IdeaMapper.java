package com.github.therycn.tyideapp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.entity.Idea;

/**
 * Idea Mapper.
 * 
 * @author TheryLeopard
 *
 */
@Mapper(componentModel = "spring")
public interface IdeaMapper {

    @Mapping(source = "workspaceId", target = "workspace.id")
    Idea to(IdeaSave ideaSave);

    @InheritInverseConfiguration
    IdeaSave to(Idea ideaSave);

}
