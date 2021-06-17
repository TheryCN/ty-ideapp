package com.github.therycn.tyideapp;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * User Update.
 */
@Getter
@Setter
public class UserUpdate {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    @Pattern(regexp = "^.{8,}$", message = "user.validation.password.length")
    @Pattern(regexp = ".*[0-9].*", message = "user.validation.password.numeric")
    private String newPassword;

    @Pattern(regexp = "^.{8,}$", message = "user.validation.password.length")
    @Pattern(regexp = ".*[0-9].*", message = "user.validation.password.numeric")
    private String password;

}
