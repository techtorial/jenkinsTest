Feature: Test Petstore APIs

  @get
  Scenario: Get pet by id
    Given pet is created with following info
      | name | id | status |
      | Hutch   | 8787  | good pet  |
    When user executes "GET" request
    Then status code is 200
    And pet has following attributes
      | petName | petId | petStatus |
      | Hutch   | 8787  | good pet  |