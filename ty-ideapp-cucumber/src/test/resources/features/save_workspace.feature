Feature: Save workspace
  I want to save a workspace

  Scenario: Save a new workspace
    Given authenticate as "Thery"
    And I want to create a workspace named "MyNewWorkspace"
    When I ask for saving workspace
    Then I should have the following workspaces:
		  | AnotherWorkspace |
		  | MyNewWorkspace   |
		  | MyWorkspace      |

  Scenario: Save an existing workspace
    Given authenticate as "Thery"
    And I want to update a workspace with id 3 with new name "MyNewUpdatedWorkspace"
    When I ask for saving workspace
    Then I should have the following workspaces:
		  | AnotherWorkspace        |
		  | MyNewUpdatedWorkspace   |
		  | MyWorkspace             |