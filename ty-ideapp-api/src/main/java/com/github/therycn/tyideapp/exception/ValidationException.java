package com.github.therycn.tyideapp.exception;

import java.util.List;

import lombok.Getter;

/**
 * Validation Exception.
 * 
 * @author TCHARASS
 *
 */
public class ValidationException extends RecoverableException {

	/** Serial version. */
	private static final long serialVersionUID = -8264870412895531834L;

	@Getter
	private List<String> errorCodes;

	public ValidationException(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public ValidationException(String message) {
		super(message);
	}

}
