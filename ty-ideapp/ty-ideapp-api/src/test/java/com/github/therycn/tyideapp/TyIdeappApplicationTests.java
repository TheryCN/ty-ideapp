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
@ActiveProfiles("test")
public class TyIdeappApplicationTests extends AbstractIntegrationTest {

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

}
