package com.github.therycn.tyideapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.therycn.tyideapp.entity.User;
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

}
