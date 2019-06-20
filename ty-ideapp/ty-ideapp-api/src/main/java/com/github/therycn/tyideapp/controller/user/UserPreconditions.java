package com.github.therycn.tyideapp.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.therycn.tyideapp.exception.ValidationException;

/**
 * User preconditions.
 * 
 * @author TCHARASS
 *
 */
@Component
public class UserPreconditions {

	public static void checkLengthAndNumeric(String newPassword) throws ValidationException {
		List<String> errorCodes = new ArrayList<>();

		// Check length
		if (!newPassword.matches("^.{8,}$")) {
			errorCodes.add("user.validation.password.length");
		}

		// Check numeric
		if (!newPassword.matches(".*[0-9].*")) {
			errorCodes.add("user.validation.password.numeric");
		}

		if (!CollectionUtils.isEmpty(errorCodes)) {
			throw new ValidationException(errorCodes);
		}
	}
}
