package com.github.therycn.tyideapp.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.exception.ValidationException;

/**
 * User preconditions.
 * 
 * @author TCHARASS
 *
 */
public final class UserPreconditions {

	/**
	 * Private constructor.
	 */
	private UserPreconditions() {
		throw new AssertionError();
	}

	public static void checkPassword(PasswordEncoder passwordEncoder, String actualPasswordEncoded,
			UserPasswordUpdate passwordUpdate) throws ValidationException {
		List<String> errorCodes = new ArrayList<>();
		String newPassword = passwordUpdate.getNewPassword();

		// Check length
		if (!newPassword.matches("^.{8,}$")) {
			errorCodes.add("user.validation.password.length");
		}

		// Check numeric
		if (!newPassword.matches(".*[0-9].*")) {
			errorCodes.add("user.validation.password.numeric");
		}

		// Compare old password
		if (!passwordEncoder.matches(passwordUpdate.getOldPassword(), actualPasswordEncoded)) {
			errorCodes.add("user.validation.password.olddifferent");
		}

		// Compare new password
		if (passwordEncoder.matches(newPassword, actualPasswordEncoded)) {
			errorCodes.add("user.validation.password.notdifferent");
		}

		if (!CollectionUtils.isEmpty(errorCodes)) {
			throw new ValidationException(errorCodes);
		}
	}

}
