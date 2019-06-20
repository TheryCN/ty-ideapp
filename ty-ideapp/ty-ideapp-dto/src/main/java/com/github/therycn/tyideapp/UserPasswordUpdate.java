package com.github.therycn.tyideapp;

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

	private String newPassword;

	private String oldPassword;

}
