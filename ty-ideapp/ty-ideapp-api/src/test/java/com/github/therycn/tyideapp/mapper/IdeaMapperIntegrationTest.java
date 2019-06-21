package com.github.therycn.tyideapp.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.therycn.tyideapp.CircleSave;
import com.github.therycn.tyideapp.IdeaSave;
import com.github.therycn.tyideapp.entity.Idea;
import com.github.therycn.tyideapp.entity.geom.Circle;
import com.google.common.collect.Iterables;

/**
 * Integration Test class {@link IdeaMapper}. Use Integration Test because we
 * are using Decorator, otherwise use Mappers#getMapper.
 * 
 * @author tcharass
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IdeaMapperIntegrationTest {

	@Autowired
	private IdeaMapper ideaMapper;

	/**
	 * Test method {@link IdeaMapper#to(Idea)}.
	 */
	@Test
	public void testIdeaToIdeaSave() {
		// Given
		Idea idea = new Idea();
		idea.setName("Test");
		idea.setLocalizations(new ArrayList<>());

		Circle circle = new Circle();
		circle.setRadius(2d);
		circle.setCoordinates(new Double[] { 1d, 1d });
		idea.getLocalizations().add(circle);

		// When
		IdeaSave ideaSave = ideaMapper.to(idea);

		// Then
		assertThat(ideaSave.getLocalizations().size()).isEqualTo(1);
		// Check that Radius is correctly setted
		assertThat(ideaSave.getLocalizations().get(0).getRadius()).isEqualTo(2d);
	}

	/**
	 * Test method {@link IdeaMapper#to(IdeaSave)}.
	 */
	@Test
	public void testIdeaSaveToIdea() {
		// Given
		IdeaSave ideaSave = new IdeaSave();
		ideaSave.setName("Test");
		ideaSave.setLocalizations(new ArrayList<>());

		CircleSave circleSave = new CircleSave();
		circleSave.setRadius(2d);
		circleSave.setCoordinates(new Double[] { 1d, 1d });
		ideaSave.getLocalizations().add(circleSave);

		// When
		Idea idea = ideaMapper.to(ideaSave);

		// Then
		assertThat(idea.getLocalizations().size()).isEqualTo(1);
		// Check that the object reference has been updated
		assertThat(Iterables.get(idea.getLocalizations(), 0).getIdea()).isEqualTo(idea);
	}

}
