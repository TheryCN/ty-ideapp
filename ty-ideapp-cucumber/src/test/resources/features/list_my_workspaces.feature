Feature: List my workspaces
  I want to get the list of my workspaces (names + ideas number)

  Scenario: List Thery's workspaces
    Given authenticate as "Thery"
    When I ask for workspaces
    Then I should have the following workspaces:
		  | AnotherWorkspace |
		  | MyWorkspace |

  Scenario: List TestUser's workspaces
    Given authenticate as "TestUser"
    When I ask for workspaces
    Then I should have the following workspaces:
		  | MyWorkspace |