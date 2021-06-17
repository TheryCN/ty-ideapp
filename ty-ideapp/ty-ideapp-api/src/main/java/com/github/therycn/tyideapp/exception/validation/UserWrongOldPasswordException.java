package com.github.therycn.tyideapp.exception.validation;

public class UserWrongOldPasswordException extends ValidationException {

    public UserWrongOldPasswordException() {
        super("user.validation.password.olddifferent");
    }

}
