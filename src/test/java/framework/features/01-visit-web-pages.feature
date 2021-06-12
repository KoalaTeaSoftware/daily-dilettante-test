@smoke
Feature: Visit Web Pages
  These scenarios smoke-test features of the test framework

  Scenario Outline: Visit some web pages
  This checks that the defaults set in the SUT configuration are internally consistent (not necessarily that they are correct).
  Pretty much everywhere gets you into the https scheme
    When I navigate to the page "<address>"
    Then the page title is "<title>"
    And the page scheme is "<resultantScheme>"
    Examples:
      | address                      | title              | resultantScheme |
      |                              |                    | https           |
      | /                            |                    | https           |
      # The following values are for more helpfully testing the framework
      | koalateasoftware.com/        | Koala Tea Software |                 |
      | http://koalateasoftware.com  | Koala Tea Software | https           |
      | https://koalateasoftware.com | Koala Tea Software | https           |


