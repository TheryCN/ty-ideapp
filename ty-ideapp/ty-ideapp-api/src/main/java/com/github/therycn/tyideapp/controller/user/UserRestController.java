package com.github.therycn.tyideapp.controller.user;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.UserNotFoundException;
import com.github.therycn.tyideapp.exception.UserWrongOldPasswordException;
import com.github.therycn.tyideapp.exception.UsernameAlreadyExistsException;
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

    @ApiOperation(value = "Get user details")
    @GetMapping("/")
    public ResponseEntity<UserInfo> getLoggedUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(userMapper.to(user));
    }

    @ApiOperation(value = "User registration")
    @PostMapping("/")
    public ResponseEntity<UserInfo> register(@Valid @RequestBody UserRegistration userRegistration)
            throws UsernameAlreadyExistsException {
        // Map to a user object
        User userToCreate = userMapper.to(userRegistration);

        // Save it with functionals checks
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.to(userService.create(userToCreate)));
    }

    @ApiOperation(value = "Update user password")
    @PatchMapping("/password")
    public ResponseEntity<UserInfo> updatePassword(@Valid @RequestBody UserPasswordUpdate passwordUpdate,
            Authentication authentication) throws UserWrongOldPasswordException, UserNotFoundException {
        // Retrieve logged user
        User user = (User) authentication.getPrincipal();

        if (!userService.checkOldPasswordEq(user, passwordUpdate.getOldPassword())) {
            throw new UserWrongOldPasswordException();
        }

        User updatedUser = userService.updatePassword(user.getId(), passwordUpdate.getNewPassword());
        return ResponseEntity.ok(userMapper.to(updatedUser));
    }

}
