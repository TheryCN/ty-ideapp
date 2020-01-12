package com.github.therycn.tyideapp.exception.validation;

import lombok.Getter;

public class UsernameAlreadyExistsException extends ValidationException {

	/** Serial version. */
	private static final long serialVersionUID = 5891533401075708109L;

	@Getter
	private String username;

	public UsernameAlreadyExistsException(String username) {
		super("user.validation.username.unicity");
		this.username = username;
	}

}
