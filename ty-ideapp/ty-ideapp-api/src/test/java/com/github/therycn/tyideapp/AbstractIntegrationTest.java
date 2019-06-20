package com.github.therycn.tyideapp;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.http.HttpHeaders;

/**
 * Abstract Integration Test.
 * 
 * @author THERY
 *
 */
public class AbstractIntegrationTest {

	public HttpHeaders createHeaders() {
		return createHeaders("Thery", "ChangeIt");
	}

	public HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			/** Serial version. */
			private static final long serialVersionUID = -4968652194130326824L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}
