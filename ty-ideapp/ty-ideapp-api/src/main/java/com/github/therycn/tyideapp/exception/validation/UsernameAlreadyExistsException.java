package com.github.therycn.tyideapp.exception.validation;

import lombok.Getter;

public class UsernameAlreadyExistsException extends ValidationException {

    @Getter
    private final String username;

    public UsernameAlreadyExistsException(String username) {
        super("user.validation.username.unicity");
        this.username = username;
    }

}
