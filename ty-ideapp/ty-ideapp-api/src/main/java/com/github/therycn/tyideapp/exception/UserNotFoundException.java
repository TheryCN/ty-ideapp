package com.github.therycn.tyideapp.exception;

public class UserNotFoundException extends ValidationException {

    /** Serial version. */
    private static final long serialVersionUID = 3170505068769537289L;

    public UserNotFoundException() {
        super("user.not.found");
    }

}
