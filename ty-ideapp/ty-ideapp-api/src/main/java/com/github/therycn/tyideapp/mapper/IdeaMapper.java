package com.github.therycn.tyideapp.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.therycn.tyideapp.CircleSave;
import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.entity.Idea;
import com.github.therycn.tyideapp.entity.geom.Circle;

/**
 * Idea Mapper.
 * 
 * @author TheryLeopard
 *
 */
@Mapper(componentModel = "spring")
@DecoratedWith(IdeaMapperDecorator.class)
public interface IdeaMapper {

	@Mapping(source = "workspaceId", target = "workspace.id")
	Idea to(IdeaSave ideaSave);

	@InheritInverseConfiguration
	IdeaSave to(Idea idea);

	Circle to(CircleSave circleSave);

	@InheritInverseConfiguration
	CircleSave to(Circle circle);

}
