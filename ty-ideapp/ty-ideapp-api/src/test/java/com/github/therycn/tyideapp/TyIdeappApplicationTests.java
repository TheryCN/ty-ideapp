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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
