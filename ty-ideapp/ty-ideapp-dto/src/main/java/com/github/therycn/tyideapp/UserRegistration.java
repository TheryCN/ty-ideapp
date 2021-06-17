package com.github.therycn.tyideapp;

import lombok.*;

import javax.validation.constraints.Pattern;

/**
 * User Update.
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
