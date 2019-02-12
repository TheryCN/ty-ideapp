package com.github.therycn.tyideapp.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.entity.Idea;
import com.github.therycn.tyideapp.entity.geom.Circle;

public abstract class IdeaMapperDecorator implements IdeaMapper {

	@Autowired
	@Qualifier("delegate")
	private IdeaMapper delegate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.therycn.tyideapp.mapper.IdeaMapper#to(com.github.therycn.tyideapp.
	 * IdeaSave)
	 */
	@Override
	public Idea to(IdeaSave ideaSave) {
		Idea idea = delegate.to(ideaSave);
		idea.getLocalizations().forEach(localization -> localization.setIdea(idea));

		return idea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.github.therycn.tyideapp.mapper.IdeaMapper#to(com.github.therycn.tyideapp.
	 * entity.Idea)
	 */
	@Override
	public IdeaSave to(Idea idea) {
		IdeaSave ideaSave = delegate.to(idea);
		ideaSave.setLocalizations(
				idea.getLocalizations().stream().map(Circle.class::cast).map(this::to).collect(Collectors.toList()));

		return ideaSave;
	}

}
