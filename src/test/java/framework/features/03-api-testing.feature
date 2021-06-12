@smoke
Feature: API verification
  As a user of this framework, so that I have confidence that I have put it together, I want to see some API test results
  Both successful, and failures

  Scenario Outline: GET from some endpoints (good and bad)
    When I get from endpoint "<endpoint>"
    Then the response status is <status>
    Examples:
      | endpoint                                         | status |
      | https://api.github.com/users/koalateasoftware    | 200    |
      | https://api.github.com/users/koalateasoftwareZZZ | 404    |

  Scenario Outline: Find data from a simple JSON endpoint
    When I get from endpoint "<endpoint>"
    Then the JSON response contains "<key>" "<value>"
    Examples:
      | endpoint                                      | key   | value            |
      | https://api.github.com/users/koalateasoftware | login | KoalaTeaSoftware |

  Scenario Outline: Look for data from a simple JSON endpoint and fail
    When I get from endpoint "<endpoint>"
    Then the JSON response contains "<key>" "<value>"
    Examples:
      | endpoint                                      | key   | value              |
      | https://api.github.com/users/koalateasoftware | login | Koala-Tea-Software |

  Scenario Outline: Find data from a simple XML endpoint
    When I get from endpoint "https://dailydilettante.tumblr.com/api/read/xml"
    Then the XML response contains "<nodeKey>" "<attributeName>" "<expect>"
    Examples:
      | nodeKey          | attributeName | expect          |
      | tumblr.tumblelog | name          | dailydilettante |

  Scenario Outline: Look for data from a simple XML endpoint and fail
    When I get from endpoint "https://dailydilettante.tumblr.com/api/read/xml"
    Then the XML response contains "<nodeKey>" "<attributeName>" "<expect>"
    Examples:
      | nodeKey          | attributeName | expect           |
      | tumblr.tumblelog | name          | daily-dilettante |
