package com.github.therycn.tyideapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.repository.UserRepository;

/**
 * Spring context + Endpoint tests.
 * 
 * @author tcharass
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class TyIdeappApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepository userRepo;

	@Test
	public void contextLoads() {
	}

	/**
	 * Test /workspace/ Endpoint.
	 */
	@Test
	public void testListWorkspaces() {
		// Given
		HttpHeaders headers = createHeaders();

		// When
		ResponseEntity<WorkspaceListItem[]> response = restTemplate.exchange("/workspace/", HttpMethod.GET,
				new HttpEntity<Object>(headers), WorkspaceListItem[].class);

		// Then
		assertThat(response.getBody().length).isEqualTo(2);
	}

	/**
	 * Test /user/ Endpoint.
	 */
	@Test
	public void testGetLoggedUser() {
		// Given
		HttpHeaders headers = createHeaders();

		// When
		ResponseEntity<UserInfo> response = restTemplate.exchange("/user/", HttpMethod.GET,
				new HttpEntity<Object>(headers), UserInfo.class);

		// Then
		assertThat(response.getBody().getUsername()).isEqualTo("Thery");
	}

	/**
	 * Test /user/password Endpoint.
	 */
	@Test
	public void testPostSavePassword() {
		User user = userRepo.findById(1L).orElse(null);
		try {
			// Given
			HttpHeaders headers = createHeaders();

			// When
			ResponseEntity<UserInfo> response = restTemplate.exchange("/user/password", HttpMethod.POST,
					new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt01", "ChangeIt"), headers), UserInfo.class);

			// Then
			assertThat(response.getBody().getUsername()).isEqualTo("Thery");
		} finally {
			// Reset password
			userRepo.save(user);
		}
	}

	/**
	 * Test /user/password Endpoint + ValidationException Handler.
	 */
	@Test
	public void testPostSavePasswordKo() {
		// Given
		HttpHeaders headers = createHeaders();

		// When
		ResponseEntity<String[]> response = restTemplate.exchange("/user/password", HttpMethod.POST,
				new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt", "ChangeIt"), headers), String[].class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	private HttpHeaders createHeaders() {
		return createHeaders("Thery", "ChangeIt");
	}

	private HttpHeaders createHeaders(String username, String password) {
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
