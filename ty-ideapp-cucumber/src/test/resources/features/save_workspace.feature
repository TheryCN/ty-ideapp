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

  Scenario: Save an existing workspace
    Given authenticate as "Thery"
    And I want to update a workspace with id 4 with new name "MyNewUpdatedWorkspace"
    When I ask for saving workspace
    And I ask for workspaces
    Then I should have the following workspaces:
		  | AnotherWorkspace        |
		  | MyNewUpdatedWorkspace   |
		  | MyWorkspace             |

  Scenario: Delete an existing workspace
    Given authenticate as "Thery"
    And I want to delete workspace with id 4
    When I ask for deleting workspace
    And I ask for workspaces
    Then I should have the following workspaces:
		  | AnotherWorkspace        |
		  | MyWorkspace             |