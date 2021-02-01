Feature: Check the flight functionality

  Background: user is logged in
    Given I set POST flight service api endpoint

  Scenario: Check the flight details
    When I set request HEADER
    And send a POST HTTP request
    Then I receive a valid Response
    When Send a Get HTTP request

  Scenario: Deleting the flight info
    Then The flight info is Removed
