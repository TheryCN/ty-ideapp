package com.github.therycn.tyideapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.ValidationException;
import com.github.therycn.tyideapp.repository.UserRepository;

/**
 * Test class {@link UserService}.
 * 
 * @author tcharass
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final String TEST_USER = "TestUser";

	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private UserService userService;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * Test method {@link UserService#loadUserByUsername(String)}.
	 */
	@Test
	public void testLoadUserByUsername() {
		// Given
		User testUser = User.builder().username(TEST_USER).password("ChangeIt").id(1L).build();
		Mockito.when(userRepo.findByUsername(Mockito.anyString())).thenReturn(testUser);

		// When
		User user = (User) userService.loadUserByUsername(TEST_USER);

		// Then
		assertThat(user).isEqualTo(testUser);
	}

	/**
	 * Test method {@link UserService#create(User)}.
	 * 
	 * @throws ValidationException
	 */
	@Test
	public void testCreateUserOk() throws ValidationException {
		// Given
		User user = User.builder().username(TEST_USER).password("ChangeIt01").build();
		Mockito.when(userRepo.save(Mockito.<User>any())).thenReturn(user);

		// No unicity constraint to check because userRepo.findByUsername will returns
		// null by default

		// When
		User createdUser = userService.create(user);

		// Then
		assertThat(user).isEqualTo(createdUser);
	}

	/**
	 * Test method {@link UserService#create(User)}; User to create has an id.
	 * 
	 * @throws ValidationException
	 */
	@Test
	public void testCreateUserKo_whenThereIsAnId() throws ValidationException {
		// Given
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("User must have no id, it's a creation !");

		User user = User.builder().id(1L).build();

		// When
		userService.create(user);

		// Then
	}

	/**
	 * Test method {@link UserService#create(User)}; User to create has a non unique
	 * username.
	 * 
	 * @throws ValidationException
	 */
	@Test
	public void testCreateUserKo_whenUsernameIsNotUnique() throws ValidationException {
		// Given
		expectedEx.expect(CombinableMatcher.both(CoreMatchers.is(CoreMatchers.instanceOf(ValidationException.class)))
				.and(Matchers.hasProperty("errorCodes", CoreMatchers.hasItems("user.validation.username.unicity"))));

		User existingUser = User.builder().id(1L).username(TEST_USER).build();
		Mockito.when(userRepo.findByUsername(TEST_USER)).thenReturn(existingUser);

		User user = User.builder().username(TEST_USER).build();

		// When
		userService.create(user);

		// Then
	}

	/**
	 * Test method {@link UserService#update(User)}.
	 * 
	 * @throws ValidationException
	 */
	@Test
	public void testUpdateUserOk() throws ValidationException {
		// Given
		User user = User.builder().id(1L).username(TEST_USER).password("ChangeIt01").build();
		Mockito.when(userRepo.save(Mockito.<User>any())).thenReturn(user);

		// When
		User createdUser = userService.update(user);

		// Then
		assertThat(user).isEqualTo(createdUser);
	}

	/**
	 * Test method {@link UserService#update(User)}; User to update has change his
	 * username with an unique.
	 * 
	 * @throws ValidationException
	 */
	@Test
	public void testUpdateUserOk_whenUsernameChangeAndIsUnique() throws ValidationException {
		// Given
		User user = User.builder().id(1L).username(TEST_USER).build();
		Mockito.when(userRepo.save(user)).thenReturn(user);
		Mockito.when(userRepo.findByUsername(TEST_USER)).thenReturn(null);

		// When
		User createdUser = userService.update(user);

		// Then
		assertThat(user).isEqualTo(createdUser);
	}

	/**
	 * Test method {@link UserService#update(User)}; User to update has a different
	 * username but his new one isn't unique.
	 * 
	 * @throws ValidationException
	 */
	@Test
	public void testUpdateUserKo_whenUsernameIsNotUnique() throws ValidationException {
		// Given
		expectedEx.expect(CombinableMatcher.both(CoreMatchers.is(CoreMatchers.instanceOf(ValidationException.class)))
				.and(Matchers.hasProperty("errorCodes", CoreMatchers.hasItems("user.validation.username.unicity"))));

		User existingUser = User.builder().id(2L).username(TEST_USER).build();
		Mockito.when(userRepo.findByUsername(TEST_USER)).thenReturn(existingUser);

		User user = User.builder().id(1L).username(TEST_USER).build();

		// When
		userService.update(user);

		// Then
	}
}
