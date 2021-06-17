package com.github.therycn.tyideapp.exception.notfound;

import com.github.therycn.tyideapp.exception.RecoverableException;

public class NotFoundException extends RecoverableException {

    public NotFoundException(String message) {
        super(message);
    }

}
