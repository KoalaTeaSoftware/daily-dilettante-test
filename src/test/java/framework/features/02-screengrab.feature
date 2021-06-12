@smoke
Feature: Takes a screengrab when a scenario fails

  Scenario: Fail a test with an interesting screen grab
    When I navigate to the page "http://koalateasoftware.com"
    And I write to the html report "The following step will cause the scenario to fail. "
    Then the page title is "qwertyuiop"
    And I write to the html report "This step should be skipped"