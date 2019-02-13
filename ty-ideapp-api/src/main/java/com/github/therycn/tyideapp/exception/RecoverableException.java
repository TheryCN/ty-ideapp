package com.github.therycn.tyideapp.exception;

/**
 * Recoverable Exception.
 * 
 * @author TCHARASS
 *
 */
public class RecoverableException extends Exception {

	/** Serial version. */
	private static final long serialVersionUID = 2225273400686485575L;

	public RecoverableException() {
		super();
	}

	public RecoverableException(String message) {
		super(message);
	}

}
