package com.github.therycn.tyideapp.controller.user;

import com.github.therycn.tyideapp.AbstractIntegrationTest;
import com.github.therycn.tyideapp.UserInfo;
import com.github.therycn.tyideapp.UserPasswordUpdate;
import com.github.therycn.tyideapp.UserRegistration;
import com.github.therycn.tyideapp.entity.User;
import com.github.therycn.tyideapp.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * UserRestController Integration Test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserRestControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // Hack for PATCH requests
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    /**
     * Test /users/ Endpoint.
     */
    @Test
    public void whenGetLoggedUser_thenReturnUserInfo() {
        // Given
        UserInfo expectedUserInfo = new UserInfo(1L, "Thery");
        HttpHeaders headers = createHeaders();

        // When
        ResponseEntity<UserInfo> responseEntity = restTemplate.exchange("/users/", HttpMethod.GET,
                new HttpEntity<Object>(headers), UserInfo.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expectedUserInfo);
    }

    @Test
    public void whenGetLoggedUser_thenReturnForbidden() {
        // Given
        // When
        ResponseEntity<UserInfo> responseEntity = restTemplate.exchange("/users/", HttpMethod.GET, null,
                UserInfo.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    /**
     * Test /users/password Endpoint.
     */
    @Test
    public void whenPatchUpdatePassword_thenReturnUserInfo() {
        // Given
        User user = userRepository.findById(1L).orElse(null);
        HttpHeaders headers = createHeaders();
        UserInfo expectedUserInfo = new UserInfo(1L, "Thery");

        try {
            // When
            ResponseEntity<UserInfo> responseEntity = restTemplate.exchange("/users/password", HttpMethod.PATCH,
                    new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt01", "ChangeIt"), headers), UserInfo.class);

            // Then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody()).isEqualTo(expectedUserInfo);
        } finally {
            // Reset password
            userRepository.save(user);
        }
    }

    /**
     * Test /users/password Endpoint + ValidationException Handler.
     */
    @Test
    public void whenPatchUpdatePassword_thenReturnBadRequest() {
        // Given
        HttpHeaders headers = createHeaders();

        // When
        ResponseEntity<String> response = restTemplate.exchange("/users/password", HttpMethod.PATCH,
                new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt", "ChangeIt#Old"), headers), String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void whenPatchUpdatePassword_thenReturnForbidden() {
        // Given

        // When
        ResponseEntity<String> response = restTemplate.exchange("/users/password", HttpMethod.PATCH,
                new HttpEntity<Object>(new UserPasswordUpdate("ChangeIt", "ChangeIt#Old"), null), String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    /**
     * Test /users/ Endpoint for user registration.
     */
    @Test
    public void whenPostRegister_thenReturnCreatedUserInfo() {
        // Given - Auth not required to create a new user
        UserRegistration userRegistration = UserRegistration.builder().username("Thery#2").password("ChangeIt001")
                .build();

        // When
        ResponseEntity<UserInfo> responseEntity = restTemplate.exchange("/users/", HttpMethod.POST,
                new HttpEntity<Object>(userRegistration), UserInfo.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getUsername()).isEqualTo("Thery#2");
    }

    /**
     * Test /users/ Endpoint for user registration ko because password has no
     * digits.
     */
    @Test
    public void whenPostRegister_PasswordLengthKO_thenReturnBadRequest() {
        // Given - Auth not required to create a new user
        UserRegistration userRegistration = UserRegistration.builder().username("Thery#2").password("ChangeIt").build();

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("password", "Add a numeric");

        // When
        ParameterizedTypeReference<Map<String, String>> mapTypeRef = new ParameterizedTypeReference<Map<String, String>>() {
        };

        ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange("/users/", HttpMethod.POST,
                new HttpEntity<Object>(userRegistration), mapTypeRef);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody()).isEqualTo(errorMap);
    }

    /**
     * Test /users/ Endpoint for user registration ko because username is already
     * used.
     */
    @Test
    public void whenPostRegister_UsernameAlreadyExists_thenReturnBadRequest() {
        // Given - Auth not required to create a new user
        UserRegistration userRegistration = UserRegistration.builder().username("Thery").password("ChangeIt001")
                .build();

        // When
        ResponseEntity<String> response = restTemplate.exchange("/users/", HttpMethod.POST,
                new HttpEntity<Object>(userRegistration), String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
