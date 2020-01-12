package com.github.therycn.tyideapp.exception.notfound;

import com.github.therycn.tyideapp.exception.RecoverableException;

public class NotFoundException extends RecoverableException {

	/**
	 * Serial version.
	 */
	private static final long serialVersionUID = -9121110062915331173L;

	public NotFoundException(String message) {
		super(message);
	}

}
