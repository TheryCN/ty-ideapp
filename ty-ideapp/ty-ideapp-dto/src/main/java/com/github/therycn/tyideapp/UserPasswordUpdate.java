package com.github.therycn.tyideapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * User Password Update.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdate {

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String oldPassword;

}
