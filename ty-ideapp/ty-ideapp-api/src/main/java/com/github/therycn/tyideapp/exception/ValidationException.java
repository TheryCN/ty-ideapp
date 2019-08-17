package com.github.therycn.tyideapp.exception;

/**
 * Validation Exception.
 * 
 * @author TCHARASS
 *
 */
public class ValidationException extends RecoverableException {

    /** Serial version. */
    private static final long serialVersionUID = -8264870412895531834L;

    public ValidationException(String message) {
        super(message);
    }

}
