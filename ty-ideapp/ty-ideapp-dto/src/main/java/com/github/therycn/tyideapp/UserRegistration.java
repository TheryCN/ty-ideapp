package com.github.therycn.tyideapp;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User Update.
 * 
 * @author THERY
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistration {

    private String username;

    private String email;

    @Pattern(regexp = "^.{8,}$", message = "user.validation.password.length")
    @Pattern(regexp = ".*[0-9].*", message = "user.validation.password.numeric")
    private String password;

}
