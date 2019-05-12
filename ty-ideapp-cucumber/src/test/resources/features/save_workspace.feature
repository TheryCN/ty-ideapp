Feature: Save workspace
  I want to save a workspace

  Scenario: Save a new workspace
    Given authenticate as "Thery"
    And I want to create a workspace named "MyNewWorkspace"
    When I ask for saving workspace
    And I ask for workspaces
    Then I should have the following workspaces:
		  | AnotherWorkspace |
		  | MyNewWorkspace   |
		  | MyWorkspace      |
	And I delete the selected workspace

  Scenario: Save an existing workspace
    Given authenticate as "Thery"
    And an existing workspace named "MyNewWorkspace"
    And I want to update it with new name "MyNewUpdatedWorkspace"
    When I ask for saving workspace
    And I ask for workspaces
    Then I should have the following workspaces:
		  | AnotherWorkspace        |
		  | MyNewUpdatedWorkspace   |
		  | MyWorkspace             |
	And I delete the selected workspace
