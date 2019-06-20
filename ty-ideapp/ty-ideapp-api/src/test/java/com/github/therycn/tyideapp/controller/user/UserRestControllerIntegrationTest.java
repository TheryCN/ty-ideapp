package com.github.therycn.tyideapp.controller.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
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
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.therycn.tyideapp.AbstractIntegrationTest;
import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.repository.UserRepository;

/**
 * UserRestController Integration Test.
 * 
 * @author THERY
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class UserRestControllerIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepository userRepo;

	@Before
	public void setup() {
		// Hack for PATCH requests
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
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
	public void testPatchSavePassword() {
		User user = userRepo.findById(1L).orElse(null);
		try {
			// Given
			HttpHeaders headers = createHeaders();

			// When
			ResponseEntity<Integer> response = restTemplate.exchange("/user/password", HttpMethod.PATCH,
					new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt01", "ChangeIt"), headers), Integer.class);

			// Then
			assertThat(response.getBody()).isEqualTo(1);
		} finally {
			// Reset password
			userRepo.save(user);
		}
	}

	/**
	 * Test /user/password Endpoint + ValidationException Handler.
	 */
	@Test
	public void testPatchSavePasswordKo() {
		// Given
		HttpHeaders headers = createHeaders();

		// When
		ResponseEntity<String[]> response = restTemplate.exchange("/user/password", HttpMethod.PATCH,
				new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt", "ChangeIt"), headers), String[].class);

		// Then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

	}

}
