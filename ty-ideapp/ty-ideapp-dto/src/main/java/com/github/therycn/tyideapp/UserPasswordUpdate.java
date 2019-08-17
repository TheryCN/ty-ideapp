package com.github.therycn.tyideapp;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Password Update.
 * 
 * @author TheryLeopard
 *
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
