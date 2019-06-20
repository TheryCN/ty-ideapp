package com.github.therycn.tyideapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import com.github.therycn.tyideapp.UserSave;
import com.github.therycn.tyideapp.entity.User;

public abstract class UserMapperDecorator implements UserMapper {

	@Autowired
	@Qualifier("delegate")
	private UserMapper delegate;

	private PasswordEncoder passwordEncoder;

	@Override
	public User to(UserSave userSave) {
		User user = delegate.to(userSave);
		if (!StringUtils.isEmpty(userSave.getNewPassword())) {
			user.setPassword(passwordEncoder.encode(userSave.getNewPassword()));
		}

		return user;
	}

	@Override
	public User updateUser(UserSave userSave, User user) {
		String existingPassword = user.getPassword();
		user = delegate.updateUser(userSave, user);

		if (!StringUtils.isEmpty(userSave.getNewPassword())) {
			user.setPassword(passwordEncoder.encode(userSave.getNewPassword()));
		} else {
			user.setPassword(existingPassword);
		}

		return user;
	}

}
