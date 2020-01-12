package com.github.therycn.tyideapp;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.github.therycn.tyideapp.repository.UserRepository;

/**
 * Spring context + Endpoint tests.
 * 
 * @author tcharass
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TyIdeappApplicationTests extends AbstractIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	/**
	 * Test /workspace/ Endpoint.
	 */
	@Test
	public void whenGetWorkspaces_thenReturnWorkspaceListItemList() {
		// Given
		HttpHeaders headers = createHeaders();
		userRepository.findAll();
		// When
		ResponseEntity<WorkspaceListItem[]> response = restTemplate.exchange("/workspaces/", HttpMethod.GET,
				new HttpEntity<Object>(headers), WorkspaceListItem[].class);

		// Then
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody().length).isEqualTo(2);
	}

}
