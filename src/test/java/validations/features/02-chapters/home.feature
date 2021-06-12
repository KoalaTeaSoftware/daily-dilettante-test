@functional @ci
Feature: Chapter: Home
  As a user, so that I can enjoy the find out abut this business, I don't want to see any errors on the home page

  Scenario Outline: Check the page's framework
    Given I navigate to the page "<relativeUrl>"
    Then the page is fully drawn
    And the page title is ""
    And the first heading is "Welcome"
    Examples:
      | relativeUrl |
      |             |

  Scenario Outline: Check page's HTML syntax
    Given the w3C HTML tester reviews the file "<relativeUrl>"
    Then the w3c HTML tester reports compliance
    Examples:
      | relativeUrl |
      |             |

  Scenario Outline: Check the page's images are well formed
    Given I navigate to the page "<relativeUrl>"
    Then all images are well formed
    Examples:
      | relativeUrl |
      |             |

  Scenario Outline: Check page's links
    Given the w3c link checker reviews the file "<relativeUrl>"
    Then the w3c link checker reports compliance
    Examples:
      | relativeUrl |
      |             |

