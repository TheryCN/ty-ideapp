package com.github.therycn.tyideapp.service;

import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.notfound.UserNotFoundException;
import com.github.therycn.tyideapp.exception.validation.UsernameAlreadyExistsException;
import com.github.therycn.tyideapp.exception.validation.ValidationException;
import com.github.therycn.tyideapp.repository.UserRepository;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class {@link UserService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String TEST_USER = "TestUser";

    @Mock
    private UserRepository userRepository;

    @Mock
    private WorkspaceRepository workspaceRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    /**
     * Test method {@link UserService#loadUserByUsername(String)}.
     */
    @Test
    public void whenLoadUserByUsername_thenReturnUser() {
        // Given
        User testUser = User.builder().username(TEST_USER).password("ChangeIt").id(1L).build();
        Mockito.when(userRepository.findByUsername(TEST_USER)).thenReturn(Optional.of(testUser));

        // When
        User user = (User) userService.loadUserByUsername(TEST_USER);

        // Then
        assertThat(user).isEqualTo(testUser);
        Mockito.verify(userRepository).findByUsername(TEST_USER);
    }

    @Test
    public void whenLoadUserByUsername_UsernameNotFound_thenThrowNewUsernameNotFoundException() {
        // Given
        Mockito.when(userRepository.findByUsername(TEST_USER)).thenReturn(Optional.empty());

        try {
            // When
            userService.loadUserByUsername(TEST_USER);
            Assertions.fail("Expected UsernameNotFoundException");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        } finally {
            // Then
            Mockito.verify(userRepository).findByUsername(TEST_USER);
        }
    }

    /**
     * Test method {@link UserService#create(User)}.
     */
    @Test
    public void whenCreateUser_UserOk_thenReturnCreatedUser() throws UsernameAlreadyExistsException {
        // Given
        User user = User.builder().username(TEST_USER).password("ChangeIt01").build();
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        // When
        User createdUser = userService.create(user);

        // Then
        assertThat(user).isEqualTo(createdUser);
        Mockito.verify(userRepository).findByUsername(user.getUsername());
        Mockito.verify(userRepository).save(user);
    }

    /**
     * Test method {@link UserService#create(User)}; User to create has an id.
     */
    @Test
    public void whenCreateUser_ThereIsAnId_thenThrowIllegalArgumentException() throws UsernameAlreadyExistsException {
        // Given
        User user = User.builder().id(1L).build();

        // When
        try {
            userService.create(user);
        } catch (Exception e) {
            // Then
            Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }

        // Then
    }

    /**
     * Test method {@link UserService#create(User)}; User to create has a non unique
     * username.
     */
    @Test
    public void whenCreateUser_UsernameIsNotUnique_thenThrowUsernameAlreadyExistsException()
            throws UsernameAlreadyExistsException {
        // Given
        User existingUser = User.builder().id(1L).username(TEST_USER).build();
        Mockito.when(userRepository.findByUsername(TEST_USER)).thenReturn(Optional.of(existingUser));

        User user = User.builder().username(TEST_USER).build();

        // When
        try {
            userService.create(user);
            Assertions.fail("Expect UsernameAlreadyExistsException");
        } catch (ValidationException e) {
            // Then
            Assertions.assertThat(e).isInstanceOf(UsernameAlreadyExistsException.class);
            UsernameAlreadyExistsException usernameAlreadyExistsException = (UsernameAlreadyExistsException) e;
            Assertions.assertThat(usernameAlreadyExistsException.getUsername()).isEqualTo(user.getUsername());
        } finally {
            Mockito.verify(userRepository).findByUsername(TEST_USER);
        }
    }

    @Test
    public void whenCheckOldPasswordEq_thenReturnTrue() {
        // Given
        String oldPassword = "ChangeIt#01Encoded";
        User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");

        Mockito.when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(true);

        // When
        boolean equality = userService.checkOldPasswordEq(user, oldPassword);

        // Then
        Assertions.assertThat(equality).isEqualTo(true);
        Mockito.verify(passwordEncoder).matches(oldPassword, user.getPassword());
    }

    @Test
    public void whenCheckOldPasswordEq_thenReturnFalse() {
        // Given
        String oldPassword = "ChangeIt#02Encoded";
        User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");

        Mockito.when(passwordEncoder.matches(oldPassword, user.getPassword())).thenReturn(false);

        // When
        boolean equality = userService.checkOldPasswordEq(user, oldPassword);

        // Then
        Assertions.assertThat(equality).isEqualTo(false);
        Mockito.verify(passwordEncoder).matches(oldPassword, user.getPassword());
    }

    @Test
    public void whenUpdatePassword_thenReturnUser() throws UserNotFoundException {
        // Given
        String newPassword = "ChangeIt#01";
        String newEncodedPassword = "ChangeIt#01Encoded";
        User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        Mockito.when(passwordEncoder.encode(newPassword)).thenReturn(newEncodedPassword);

        // When
        User updatedUser = userService.updatePassword(user.getId(), newPassword);

        // Then
        Assertions.assertThat(updatedUser).isEqualTo(user);
        Mockito.verify(userRepository).updatePassword(user.getId(), newEncodedPassword);
        Mockito.verify(userRepository).findById(user.getId());
    }

    @Test
    public void whenUpdatePassword_thenThrowUserNotFoundException() {
        // Given
        String newPassword = "ChangeIt#01";

        User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // When
        try {
            userService.updatePassword(user.getId(), newPassword);
            Assertions.fail("Expect UserNotFoundException");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }
}
