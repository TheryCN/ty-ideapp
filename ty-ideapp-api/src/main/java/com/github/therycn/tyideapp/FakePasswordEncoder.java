package com.github.therycn.tyideapp;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Fake password encoder.
 * 
 * @author THERY
 *
 */
public class FakePasswordEncoder implements PasswordEncoder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.crypto.password.PasswordEncoder#encode(java.lang
	 * .CharSequence)
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.crypto.password.PasswordEncoder#matches(java.
	 * lang.CharSequence, java.lang.String)
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

}
