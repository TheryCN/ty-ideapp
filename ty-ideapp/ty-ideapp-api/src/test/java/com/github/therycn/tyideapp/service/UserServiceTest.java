package com.github.therycn.tyideapp.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.UserNotFoundException;
import com.github.therycn.tyideapp.exception.UsernameAlreadyExistsException;
import com.github.therycn.tyideapp.exception.ValidationException;
import com.github.therycn.tyideapp.repository.UserRepository;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;

/**
 * Test class {@link UserService}.
 * 
 * @author tcharass
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String TEST_USER = "TestUser";

    @Mock
    private UserRepository userRepo;

    @Mock
    private WorkspaceRepository workspaceRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * Test method {@link UserService#loadUserByUsername(String)}.
     */
    @Test
    public void loadUserByUsername_UserFound_User() {
        // Given
        User testUser = User.builder().username(TEST_USER).password("ChangeIt").id(1L).build();
        Mockito.when(userRepo.findByUsername(TEST_USER)).thenReturn(Optional.of(testUser));

        // When
        User user = (User) userService.loadUserByUsername(TEST_USER);

        // Then
        assertThat(user).isEqualTo(testUser);
        Mockito.verify(userRepo).findByUsername(TEST_USER);
    }

    @Test
    public void loadUserByUsername_UsernameNotFound_ThrowNewUsernameNotFoundException() {
        // Given
        Mockito.when(userRepo.findByUsername(TEST_USER)).thenReturn(Optional.empty());

        try {
            // When
            userService.loadUserByUsername(TEST_USER);
            Assertions.fail("Expected UsernameNotFoundException");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        } finally {
            // Then
            Mockito.verify(userRepo).findByUsername(TEST_USER);
        }
    }

    /**
     * Test method {@link UserService#create(User)}.
     * 
     * @throws ValidationException
     */
    @Test
    public void createUser_UserOk_CreatedUser() throws UsernameAlreadyExistsException {
        // Given
        User user = User.builder().username(TEST_USER).password("ChangeIt01").build();
        Mockito.when(userRepo.save(user)).thenReturn(user);
        Mockito.when(userRepo.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        // When
        User createdUser = userService.create(user);

        // Then
        assertThat(user).isEqualTo(createdUser);
        Mockito.verify(userRepo).findByUsername(user.getUsername());
        Mockito.verify(userRepo).save(user);
    }

    /**
     * Test method {@link UserService#create(User)}; User to create has an id.
     * 
     * @throws ValidationException
     */
    @Test
    public void createUser_ThereIsAnId_ThrowIllegalArgumentException() throws UsernameAlreadyExistsException {
        // Given
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("User must have no id, it's a creation !");

        User user = User.builder().id(1L).build();

        // When
        userService.create(user);

        // Then
    }

    /**
     * Test method {@link UserService#create(User)}; User to create has a non
     * unique username.
     * 
     * @throws ValidationException
     */
    @Test
    public void createUser_UsernameIsNotUnique_ThrowUsernameAlreadyExistsException()
            throws UsernameAlreadyExistsException {
        // Given
        User existingUser = User.builder().id(1L).username(TEST_USER).build();
        Mockito.when(userRepo.findByUsername(TEST_USER)).thenReturn(Optional.of(existingUser));

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
            Mockito.verify(userRepo).findByUsername(TEST_USER);
        }
    }

    @Test
    public void checkOldPasswordEq_PasswordAreEquals_True() {
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
    public void checkOldPasswordEq_PasswordAreDifferent_False() {
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
    public void updatePassword_UserFound_UserIsUpToDate() throws UserNotFoundException {
        // Given
        String newPassword = "ChangeIt#01";
        String newEncodedPassword = "ChangeIt#01Encoded";
        User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");
        Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));

        Mockito.when(passwordEncoder.encode(newPassword)).thenReturn(newEncodedPassword);

        // When
        User updatedUser = userService.updatePassword(user.getId(), newPassword);

        // Then
        Assertions.assertThat(updatedUser).isEqualTo(user);
        Mockito.verify(userRepo).updatePassword(user.getId(), newEncodedPassword);
        Mockito.verify(userRepo).findById(user.getId());
    }

    @Test
    public void updatePassword_UserNotFound_UserNotFoundException() {
        // Given
        String newPassword = "ChangeIt#01";

        User user = new User(1l, "Thery", "thery@github.com", "ChangeIt#01Encoded");
        Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.empty());

        // When
        try {
            userService.updatePassword(user.getId(), newPassword);
            Assertions.fail("Expect UserNotFoundException");
        } catch (ValidationException e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        } finally {
            Mockito.verify(userRepo).findById(user.getId());
        }
    }
}
