package com.github.therycn.tyideapp.exception.notfound;

public class UserNotFoundException extends NotFoundException {

	/** Serial version. */
	private static final long serialVersionUID = 3170505068769537289L;

	public UserNotFoundException() {
		super("user.not.found");
	}

}
