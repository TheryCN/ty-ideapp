package com.github.therycn.tyideapp.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserPasswordUpdate;
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

	private PasswordEncoder passwordEncoder;

	@ApiOperation(value = "Get user details")
	@GetMapping("/")
	public UserInfo getLoggedUser(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return userMapper.to(user);
	}

	@ApiOperation(value = "Update user password")
	@PostMapping("/password")
	public UserInfo savePassword(@RequestBody UserPasswordUpdate passwordUpdate, Authentication authentication)
			throws ValidationException {
		User user = (User) authentication.getPrincipal();
		UserPreconditions.checkPassword(passwordEncoder, user.getPassword(), passwordUpdate);

		String newPasswordEncoded = passwordEncoder.encode(passwordUpdate.getNewPassword());
		user.setPassword(newPasswordEncoded);
		return userMapper.to(userService.save(user));
	}

}
