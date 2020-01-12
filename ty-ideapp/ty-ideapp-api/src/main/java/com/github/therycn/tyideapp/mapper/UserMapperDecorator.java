package com.github.therycn.tyideapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.service.UserService;

public abstract class UserMapperDecorator implements UserMapper {

	@Autowired
	@Qualifier("delegate")
	private UserMapper delegate;

	@Autowired
	private UserService userService;

	@Override
	public User to(UserRegistration userRegistration) {
		User user = delegate.to(userRegistration);
		user.setPassword(userService.encodePassword(userRegistration.getPassword()));

		return user;
	}

}