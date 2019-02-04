package com.github.therycn.tyideapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.mapper.UserMapper;

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

	@GetMapping("/")
	public UserInfo getLoggedUser(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return userMapper.to(user);
	}
}
