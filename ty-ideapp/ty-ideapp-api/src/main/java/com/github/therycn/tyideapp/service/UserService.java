package com.github.therycn.tyideapp.service;

import java.util.Arrays;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.exception.ValidationException;
import com.github.therycn.tyideapp.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * User Service.
 * 
 * @author tcharass
 *
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository userRepo;

	private PasswordEncoder passwordEncoder;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}

	/**
	 * Get user by id.
	 * 
	 * @param id
	 * @return {@link User}
	 */
	public User getUser(Long id) {
		return userRepo.getOne(id);
	}

	/**
	 * Create a new user.
	 * 
	 * @param user {@link User}
	 * @return {@link User}
	 * @throws ValidationException
	 */
	@Transactional
	public User create(User user) throws ValidationException {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User must have no id, it's a creation !");
		}

		if (!hasUniqueUsername(user.getId(), user.getUsername())) {
			throw new ValidationException(Arrays.asList("user.validation.username.unicity"));
		}
		return userRepo.save(user);
	}

	/**
	 * Update an existing user.
	 * 
	 * @param user {@link User}
	 * @return {@link User}
	 * @throws ValidationException
	 */
	@Transactional
	public User update(User user) throws ValidationException {
		// If username has changed then check unicity
		if (!hasUniqueUsername(user.getId(), user.getUsername())) {
			throw new ValidationException(Arrays.asList("user.validation.username.unicity"));
		}

		return userRepo.save(user);
	}

	/**
	 * Check if the old password is correct.
	 * 
	 * @param user        {@link User}
	 * @param oldPassword the old password not encoded
	 * @return true if it's ok
	 */
	public boolean checkOldPasswordEq(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

	/**
	 * Encode the given password.
	 * 
	 * @param nonEncodedPassword the non encoded password
	 * @return the encoded password
	 */
	public String encodePassword(String nonEncodedPassword) {
		return passwordEncoder.encode(nonEncodedPassword);
	}

	/**
	 * Update user password.
	 * 
	 * @param id                 the user id
	 * @param nonEncodedPassword the non encoded password
	 * @return 1 if it has been updated
	 */
	@Transactional
	public int updatePassword(Long id, String nonEncodedPassword) {
		return userRepo.updatePassword(id, encodePassword(nonEncodedPassword));
	}

	private boolean hasUniqueUsername(Long id, String username) {
		User user = userRepo.findByUsername(username);
		return user == null || user.getId().equals(id);
	}

}
