package com.github.therycn.tyideapp.controller.user;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.exception.ValidationException;

/**
 * Test class {@link UserPreconditions}.
 * 
 * @author tcharass
 *
 */
public class UserPreconditionsTest {

	private PasswordEncoder encoder;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void init() {
		encoder = Mockito.mock(PasswordEncoder.class);
		Mockito.doAnswer(invocation -> (invocation.getArguments())[0]).when(encoder)
				.encode(Mockito.<CharSequence>any());
		Mockito.when(encoder.matches(Mockito.anyString(), Mockito.anyString()))
				.thenAnswer(invocation -> (invocation.getArguments())[0].equals((invocation.getArguments())[1]));
	}

	@Test
	public void testCheckPasswordOk() throws ValidationException {
		// Given + When
		checkPassword("ChangeIt", "ChangeIt01", "ChangeIt");

		// Then - no exception thrown
	}

	@Test
	public void testCheckPasswordKo_withLengthAndNumericAndOldDifferentErrors() throws ValidationException {
		// Given
		expectedEx.expect(CombinableMatcher.both(CoreMatchers.is(CoreMatchers.instanceOf(ValidationException.class)))
				.and(Matchers.hasProperty("errorCodes", CoreMatchers.hasItems("user.validation.password.length",
						"user.validation.password.numeric", "user.validation.password.olddifferent"))));

		// Given + When
		checkPassword("ChangeIt", "Change", "Wrong");

		// Then - expectedEx
	}

	@Test
	public void testCheckPasswordKo_withNumericAndNotDifferentErrors() throws ValidationException {
		// Given
		expectedEx.expect(CombinableMatcher.both(CoreMatchers.is(CoreMatchers.instanceOf(ValidationException.class)))
				.and(Matchers.hasProperty("errorCodes", CoreMatchers.hasItems("user.validation.password.numeric",
						"user.validation.password.notdifferent"))));

		// Given + When
		checkPassword("ChangeIt", "ChangeIt", "ChangeIt");

		// Then - expectedEx
	}

	private void checkPassword(String currentPassword, String newPassword, String oldPassword)
			throws ValidationException {
		UserPasswordUpdate userPasswordUpdate = new UserPasswordUpdate();
		userPasswordUpdate.setNewPassword(newPassword);
		userPasswordUpdate.setOldPassword(oldPassword);

		// When
		UserPreconditions.checkPassword(encoder, currentPassword, userPasswordUpdate);
	}

}
