package com.github.therycn.tyideapp.exception.notfound;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("user.not.found");
    }

}
