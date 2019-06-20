package com.github.therycn.tyideapp.service;

import java.util.Arrays;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

	public User getUser(Long id) {
		return userRepo.getOne(id);
	}

	public User create(User user) throws ValidationException {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User must have no id, it's a creation !");
		}

		if (!hasUniqueUsername(user.getId(), user.getUsername())) {
			throw new ValidationException(Arrays.asList("user.validation.username.unicity"));
		}
		return userRepo.save(user);
	}

	public User update(User user) throws ValidationException {
		// If username has changed then check unicity
		if (!hasUniqueUsername(user.getId(), user.getUsername())) {
			throw new ValidationException(Arrays.asList("user.validation.username.unicity"));
		}

		return userRepo.save(user);
	}

	private boolean hasUniqueUsername(Long id, String username) {
		User user = userRepo.findByUsername(username);
		return user == null || user.getId().equals(id);
	}

}
