package com.github.therycn.tyideapp.controller.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.notfound.UserNotFoundException;
import com.github.therycn.tyideapp.exception.validation.UserWrongOldPasswordException;
import com.github.therycn.tyideapp.exception.validation.UsernameAlreadyExistsException;
import com.github.therycn.tyideapp.exception.validation.ValidationException;
import com.github.therycn.tyideapp.mapper.UserMapper;
import com.github.therycn.tyideapp.service.UserService;

/**
 * Test class {@link UserRestController}.
 * 
 * @author TheryLeopard
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

	@Mock
	private UserMapper userMapper;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserRestController userRestController;

	@Test
	public void whenGetLoggedUser_thenReturnUserInfo() {
		// Given
		Authentication auth = Mockito.mock(Authentication.class);
		User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01");
		Mockito.when(auth.getPrincipal()).thenReturn(user);

		UserInfo expectedUserInfo = new UserInfo(1l, "Thery");
		Mockito.when(userMapper.to(user)).thenReturn(expectedUserInfo);

		// When
		ResponseEntity<UserInfo> responseEntity = userRestController.getLoggedUser(auth);

		// Then
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody()).isEqualTo(expectedUserInfo);

		Mockito.verify(userMapper).to(user);
	}

	@Test
	public void whenRegister_thenReturnCreatedUserInfo() throws ValidationException {
		// Given
		UserInfo expectedUserInfo = new UserInfo(1l, "Thery");
		UserRegistration userRegistration = new UserRegistration("Thery", "thery@github.com", "ChangeIt#01");

		User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01");
		Mockito.when(userMapper.to(userRegistration)).thenReturn(user);
		Mockito.when(userService.create(user)).thenReturn(user);
		Mockito.when(userMapper.to(user)).thenReturn(expectedUserInfo);

		// When
		ResponseEntity<UserInfo> responseEntity = userRestController.register(userRegistration);

		// Then
		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		Assertions.assertThat(responseEntity.getBody()).isEqualTo(expectedUserInfo);

		Mockito.verify(userMapper).to(userRegistration);
		Mockito.verify(userService).create(user);
		Mockito.verify(userMapper).to(user);
	}

	@Test
	public void whenRegister_thenThrowUsernameAlreadyExistsException() throws ValidationException {
		// Given
		UserRegistration userRegistration = new UserRegistration("Thery", "thery@github.com", "ChangeIt#01");

		User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01");
		Mockito.when(userMapper.to(userRegistration)).thenReturn(user);
		Mockito.when(userService.create(user)).thenThrow(new UsernameAlreadyExistsException(user.getUsername()));

		try {
			// When
			userRestController.register(userRegistration);
			Assertions.fail("Expect UsernameAlreadyExistsException");
		} catch (Exception e) {
			Mockito.verify(userMapper).to(userRegistration);
			Mockito.verify(userService).create(user);
			Assertions.assertThat(e).isInstanceOf(UsernameAlreadyExistsException.class);
		}
	}

	@Test
	public void whenUpdatePassword_thenReturnUserInfo() throws UserWrongOldPasswordException, UserNotFoundException {
		// Given
		UserInfo expectedUserInfo = new UserInfo(1l, "Thery");

		UserPasswordUpdate userPasswordUpdate = new UserPasswordUpdate("ChangeIt#02", "ChangeIt#01");
		Authentication auth = Mockito.mock(Authentication.class);

		User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");
		Mockito.when(auth.getPrincipal()).thenReturn(user);
		Mockito.when(userService.checkOldPasswordEq(user, userPasswordUpdate.getOldPassword())).thenReturn(true);

		User updatedUser = new User(1l, "Thery", "thery@github.com", "ChangeIt#02Encoded");
		Mockito.when(userService.updatePassword(user.getId(), userPasswordUpdate.getNewPassword()))
				.thenReturn(updatedUser);

		Mockito.when(userMapper.to(updatedUser)).thenReturn(expectedUserInfo);

		// When
		ResponseEntity<UserInfo> responseEntity = userRestController.updatePassword(userPasswordUpdate, auth);

		// Then
		Mockito.verify(userService).checkOldPasswordEq(user, userPasswordUpdate.getOldPassword());
		Mockito.verify(userService).updatePassword(user.getId(), userPasswordUpdate.getNewPassword());
		Mockito.verify(userMapper).to(updatedUser);

		Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(responseEntity.getBody()).isEqualTo(expectedUserInfo);
	}

	@Test
	public void whenUpdatePassword_thenThrowUserWrongOldPasswordException() {
		// Given
		UserPasswordUpdate userPasswordUpdate = new UserPasswordUpdate("ChangeIt#02", "ChangeIt#01");

		Authentication auth = Mockito.mock(Authentication.class);
		User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");
		Mockito.when(auth.getPrincipal()).thenReturn(user);

		Mockito.when(userService.checkOldPasswordEq(user, userPasswordUpdate.getOldPassword())).thenReturn(false);

		try {
			// When
			userRestController.updatePassword(userPasswordUpdate, auth);
			Assertions.fail("Expect UserWrongOldPasswordException");
		} catch (Exception e) {
			// Then
			Mockito.verify(userService).checkOldPasswordEq(user, userPasswordUpdate.getOldPassword());
			Assertions.assertThat(e).isInstanceOf(UserWrongOldPasswordException.class);
		} finally {
			Mockito.verify(userService).checkOldPasswordEq(user, userPasswordUpdate.getOldPassword());
		}
	}

}
