package com.github.therycn.tyideapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;

import com.github.therycn.tyideapp.UserSave;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.service.UserService;

public abstract class UserMapperDecorator implements UserMapper {

	@Autowired
	@Qualifier("delegate")
	private UserMapper delegate;

	@Autowired
	private UserService userService;

	@Override
	public User to(UserSave userSave) {
		User user = delegate.to(userSave);
		if (!StringUtils.isEmpty(userSave.getNewPassword())) {
			user.setPassword(userService.encodePassword(userSave.getNewPassword()));
		}

		return user;
	}

	@Override
	public User updateUser(UserSave userSave, User user) {
		String existingPassword = user.getPassword();
		user = delegate.updateUser(userSave, user);

		if (!StringUtils.isEmpty(userSave.getNewPassword())) {
			user.setPassword(userService.encodePassword(userSave.getNewPassword()));
		} else {
			user.setPassword(existingPassword);
		}

		return user;
	}

}
