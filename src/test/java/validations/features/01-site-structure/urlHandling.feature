@functional @ci
Feature: Friendly URLs
  As visitor, if my bookmark is out of date, I still want to see a good page
  It may be good to test this functionality because it is likely that either the SUT's configuration, or the SUT's code
  govern this behaviour, so could contain a fault.
  The likelihood of regression is low, once it works, but the test is very cheap, hence it is in the CI set.
  The assumption of these samples is that the web site redirect to a page that has the configured default time
  (so, probably, is Home). If the site has a different response to bad paths, then you will have ti alter this test

  Scenario Outline: Friendly treatment of rubbish urls
    Given I navigate to the page "<url>"
    Then the page title is ""
    Examples:
      | url             |
      | /engine-trouble |
      | /pigs/are/great |