Feature: Validate Resource

  Scenario: Validate Json resources successful
    Given a valid resource with equals json
    When user validate resource
    Then a valid json encoded should be return

  Scenario: Validate Json resources with differences
    Given a valid resource with differences
    When user validate resource
    Then the application should return json different error

  Scenario: List all resources should return a valid List
    Given a valid resource with equals json
    When all resources are listed
    Then all the resources should be returned

