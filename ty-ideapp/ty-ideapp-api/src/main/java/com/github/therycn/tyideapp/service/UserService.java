package com.github.therycn.tyideapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.entity.Workspace;
import com.github.therycn.tyideapp.exception.notfound.UserNotFoundException;
import com.github.therycn.tyideapp.exception.validation.UsernameAlreadyExistsException;
import com.github.therycn.tyideapp.exception.validation.ValidationException;
import com.github.therycn.tyideapp.repository.UserRepository;
import com.github.therycn.tyideapp.repository.WorkspaceRepository;

/**
 * User Service.
 * 
 * @author tcharass
 *
 */
@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepo;

	private WorkspaceRepository workspaceRepo;

	private PasswordEncoder passwordEncoder;

	@Value("${default.workspace.name}")
	private String defaultWorkspaceName;

	public UserService(UserRepository userRepo, WorkspaceRepository workspaceRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.workspaceRepo = workspaceRepo;
		this.passwordEncoder = passwordEncoder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
	}

	public User getUser(Long id) throws UserNotFoundException {
		return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
	}

	/**
	 * Create a new user.
	 * 
	 * @param user {@link User}
	 * @return {@link User}
	 * @throws ValidationException
	 */
	@Transactional
	public User create(User user) throws UsernameAlreadyExistsException {
		if (user.getId() != null) {
			throw new IllegalArgumentException("User must have no id, it's a creation !");
		}

		if (userRepo.findByUsername(user.getUsername()).isPresent()) {
			throw new UsernameAlreadyExistsException(user.getUsername());
		}

		User savedUser = userRepo.save(user);

		Workspace defaultWorkspace = new Workspace();
		defaultWorkspace.setName(defaultWorkspaceName);
		defaultWorkspace.setUser(savedUser);

		workspaceRepo.save(defaultWorkspace);

		return savedUser;
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
	 * @return {@link User}
	 * @throws UserNotFoundException
	 */
	@Transactional
	public User updatePassword(Long id, String nonEncodedPassword) throws UserNotFoundException {
		User user = getUser(id);
		userRepo.updatePassword(id, encodePassword(nonEncodedPassword));
		return user;
	}

}
