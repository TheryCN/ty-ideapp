package com.github.therycn.tyideapp.controller.user;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.core.CombinableMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.therycn.tyideapp.exception.ValidationException;

/**
 * Test class {@link UserPreconditions}.
 * 
 * @author tcharass
 *
 */
public class UserPreconditionsTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testCheckPasswordOk() throws ValidationException {
		// Given + When
		UserPreconditions.checkLengthAndNumeric("ChangeIt001");

		// Then - no exception thrown
	}

	@Test
	public void testCheckPasswordKo_withLengthAndNumericErrors() throws ValidationException {
		// Given
		expectedEx.expect(CombinableMatcher.both(CoreMatchers.is(CoreMatchers.instanceOf(ValidationException.class)))
				.and(Matchers.hasProperty("errorCodes",
						CoreMatchers.hasItems("user.validation.password.length", "user.validation.password.numeric"))));

		// Given + When
		UserPreconditions.checkLengthAndNumeric("Change");

		// Then - expectedEx
	}

	@Test
	public void testCheckPasswordKo_withNumericError() throws ValidationException {
		// Given
		expectedEx.expect(CombinableMatcher.both(CoreMatchers.is(CoreMatchers.instanceOf(ValidationException.class)))
				.and(Matchers.hasProperty("errorCodes", CoreMatchers.hasItems("user.validation.password.numeric"))));

		// Given + When
		UserPreconditions.checkLengthAndNumeric("ChangeItTodo");

		// Then - expectedEx
	}

}
