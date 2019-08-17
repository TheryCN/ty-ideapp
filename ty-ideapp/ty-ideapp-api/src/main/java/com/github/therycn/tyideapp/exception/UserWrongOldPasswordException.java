package com.github.therycn.tyideapp.exception;

public class UserWrongOldPasswordException extends ValidationException {

    /** Serial version. */
    private static final long serialVersionUID = 8319699197652295761L;

    public UserWrongOldPasswordException() {
        super("user.validation.password.olddifferent");
    }

}
