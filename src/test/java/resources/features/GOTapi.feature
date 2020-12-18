Feature: Test Pokemon APIs

  @get
  Scenario: Test get request
    When user sends get request for GOT
    Then status code must be 200
