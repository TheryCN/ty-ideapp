package com.github.therycn.tyideapp.controller.user;

import java.util.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.UserUpdate;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.ValidationException;
import com.github.therycn.tyideapp.mapper.UserMapper;
import com.github.therycn.tyideapp.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

/**
 * User Rest Controller.
 * 
 * @author tcharass
 *
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {

	private UserMapper userMapper;

	private UserService userService;

	@ApiOperation(value = "User registration")
	@PostMapping("/")
	public UserInfo register(@RequestBody UserRegistration userRegistration) throws ValidationException {
		// Check Inputs
		UserPreconditions.checkLengthAndNumeric(userRegistration.getPassword());

		// Map to a user object
		User userToCreate = userMapper.to(userRegistration);

		// Save it with functionals checks
		return userMapper.to(userService.create(userToCreate));
	}

	@ApiOperation(value = "Update user")
	@PutMapping("/")
	public UserInfo update(@RequestBody UserUpdate userUpdate, Authentication authentication)
			throws ValidationException {
		// Retrieve logged user
		User user = (User) authentication.getPrincipal();

		if (!StringUtils.isEmpty(userUpdate.getNewPassword())) {
			// Check Inputs
			UserPreconditions.checkLengthAndNumeric(userUpdate.getNewPassword());

			if (!userService.checkOldPasswordEq(user, userUpdate.getPassword())) {
				throw new ValidationException(Arrays.asList("user.validation.password.olddifferent"));
			}
		}

		// Map changes to the existing user
		userMapper.updateUser(userUpdate, user);

		// Save it with model checks
		return userMapper.to(userService.update(user));
	}

	@ApiOperation(value = "Update user password")
	@PatchMapping("/password")
	public int updatePassword(@RequestBody UserPasswordUpdate passwordUpdate, Authentication authentication)
			throws ValidationException {
		// Retrieve logged user
		User user = (User) authentication.getPrincipal();

		// Check Inputs
		UserPreconditions.checkLengthAndNumeric(passwordUpdate.getNewPassword());

		if (!userService.checkOldPasswordEq(user, passwordUpdate.getOldPassword())) {
			throw new ValidationException(Arrays.asList("user.validation.password.olddifferent"));
		}

		return userService.updatePassword(user.getId(), passwordUpdate.getNewPassword());
	}

	@ApiOperation(value = "Get user details")
	@GetMapping("/")
	public UserInfo getLoggedUser(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return userMapper.to(user);
	}

}
