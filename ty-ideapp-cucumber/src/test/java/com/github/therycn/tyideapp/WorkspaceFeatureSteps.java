package com.github.therycn.tyideapp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.assertj.core.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;

/**
 * List My Workspaces feature class.
 * 
 * @author THERY
 *
 */
@Slf4j
public class WorkspaceFeatureSteps {

    private String baseUri;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers;

    private ResponseEntity<WorkspaceListItem[]> response = null;

    private WorkspaceSave workspaceSave;

    private Long selectedWorkspaceId;

    @Given("authenticate as {string}")
    public void authenticate_as(String user) {
        initProperties();
        headers = createHeaders(user, "ChangeIt");
    }

    @When("I ask for workspaces")
    public void i_ask_for_workspaces() {
        response = restTemplate.exchange(baseUri + "/workspace/", HttpMethod.GET, new HttpEntity<Object>(headers),
                WorkspaceListItem[].class);
    }

    @Then("I should have the following workspaces:")
    public void i_should_have_the_following_workspaces(List<String> expectedAnswers) {
        WorkspaceListItem[] body = response.getBody();
        for (int i = 0; i < expectedAnswers.size(); i++) {
            Assertions.assertThat(body[i].getName()).isEqualTo(expectedAnswers.get(i));
        }
    }

    @Given("I want to create a workspace named {string}")
    public void i_want_to_create_a_workspace_named(String name) {
        workspaceSave = new WorkspaceSave();
        workspaceSave.setName(name);
    }

    @When("I ask for saving workspace")
    public void i_ask_for_saving_workspace() {
        ResponseEntity<WorkspaceListItem> response = restTemplate.exchange(baseUri + "/workspace/", HttpMethod.POST,
                new HttpEntity<Object>(workspaceSave, headers), WorkspaceListItem.class);
        selectedWorkspaceId = response.getBody().getId();
    }

    @Given("I want to update it with new name {string}")
    public void i_want_to_update_it_with_new_name(String name) {
        workspaceSave = new WorkspaceSave();
        workspaceSave.setId(selectedWorkspaceId);
        workspaceSave.setName(name);
    }

    @Given("an existing workspace named {string}")
    public void an_existing_workspace_named(String name) {
        workspaceSave = new WorkspaceSave();
        workspaceSave.setName(name);
        ResponseEntity<WorkspaceListItem> response = restTemplate.exchange(baseUri + "/workspace/", HttpMethod.POST,
                new HttpEntity<Object>(workspaceSave, headers), WorkspaceListItem.class);
        selectedWorkspaceId = response.getBody().getId();
    }

    @When("I delete the selected workspace")
    public void i_delete_the_selected_workspace() {
        restTemplate.exchange(baseUri + "/workspaces/" + selectedWorkspaceId, HttpMethod.DELETE,
                new HttpEntity<Object>(headers), Void.class);
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

    public void initProperties() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
            baseUri = properties.getProperty("base.uri");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
