@functional @ci
Feature: Contact Form operation & client-side verification
  The contact form should ensure that:
  * data is provided in all of the fields
  * the data is useful / not harmful
  * provide the user with feedback when:
  ** It does not like the user's entries
  ** The email is sent
  ** When the email is not sent

# Rule: The form starts life in a non-sendable state
  Scenario: Initial state of page
    When I navigate to the page "contact"
    Then the page is fully drawn
    And the first heading is "Contact"
    And the submit button is disabled

#  Rule: If a message is successfully sent, the user knows it
  Scenario: Send a message
  As this is the first scenario to be run, if is succeeds, it give confidence that the following failures are not false negatives
    Given I navigate to the page "contact"
    When I enter the following data
      | name     | Donald Duck                                                                      |
      | address1 | alpha@bet1asdfghbet2asdfghbet3asdfghbet4asdfgh.com                               |
      | address2 | alpha@bet1asdfghbet2asdfghbet3asdfghbet4asdfgh.com                               |
      | subject  | This is a test message, please delete it                                         |
      | message  | If you don't delete it, it will just clutter up you inbox and you won't be happy |
    And I send the message
    Then confirmation of sending is shown
    And the sending was successful
    And the submit button is disabled

#  Rule: If the information supplied by the user is inadequate or illegal, the message will not be sent
  Scenario Outline: Partially fill the form
  Using values (except for the missing value) that we saw work in the previous scenario
    Given I navigate to the page "contact"
    When I enter the following data
      | name     | <name>     |
      | address1 | <address1> |
      | address2 | <address2> |
      | subject  | <subject>  |
      | message  | <message>  |
    Then the submit button is disabled
    Examples:
      | name        | address1      | address2      | subject                                  | message                                                                          |
      |             | pi@staker.com | pi@staker.com | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck |               | pi@staker.com | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck | pi@staker.com |               | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck | pi@staker.com | pi@staker.com |                                          | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck | pi@staker.com | pi@staker.com | This is a test message, please delete it |                                                                                  |

# Rule: The form will only allow the user to enter the allowed number characters into the each field
  Scenario: Provide over-long email addresses
  const emailLengthMax = 50
    Given I navigate to the page "contact"
    When I enter the following data
      | name     |                                                      |
      | address1 | alpha@bet1asdfghbet2asdfghbet3asdfghbet4asdfghi.com  |
      | address2 | alpha@bet1asdfghbet2asdfghbet3asdfghbet4asdfghiq.com |
      | subject  |                                                      |
      | message  |                                                      |
    Then the email1 field contains 50 characters
    And the email2 field contains 50 characters

  Scenario Outline: Provide an over-long name
  const nameLengthMax = 40
    Given I navigate to the page "contact"
    When I enter the following data
      | name     | <name> |
      | address1 |        |
      | address2 |        |
      | subject  |        |
      | message  |        |
    Then the name field contains 40 characters
    Examples:
      | name                                      |
      | Donald Duck Donald Duck Donald Donald Qua |

  Scenario: Provide overlong subject
  const subjectLengthMax = 50
    Given I navigate to the page "contact"
    When I enter the following data
      | name     |                                                     |
      | address1 |                                                     |
      | address2 |                                                     |
      | subject  | Lorem ipsum dolor sit amet, consectetur erat curae. |
      | message  |                                                     |
    Then the subject field contains 50 characters

  Scenario: Provide overlong message
  const msgLengthMax = 5000
    Given I navigate to the page "contact"
    When I enter a message 5001 chars long
    Then the message field contains 5000 characters

# Rule: The use has to enter the exact same email address twice
# in the hope that this increases the probability that they make no typo
  Scenario Outline: Provide non-matching email addresses
    Given I navigate to the page "contact"
    When I enter the following data
      | name     |            |
      | address1 | <address1> |
      | address2 | <address2> |
      | subject  |            |
      | message  |            |
    Then the submit button is disabled
    Examples:
      | address1       | address2       |
      | pi@staker.com  | pi@skater.com  |
      | alpha@beta.com | beta@alpha.com |

  Scenario Outline: Provide an illegal entries
  The form closely constrains the range of allowed chars in fields in an attempt to reduce 'trolling'
  This is not an exhaustive test of bad chars, just of the principle - server side filtration is the back-stop
  Data is based on the 'good' data seen in the successful send
  Rely on th browser to enforce email addresses verification
  const nameRegexp = /^[a-z0-9., -]+$/i
  const subjectRegexp = /^[£a-z0-9., -]+$/i
  const msgRegexp = /^[£a-z0-9., -/?/)(\n]+$/i
    Given I navigate to the page "contact"
    When I enter the following data
      | name     | <name>        |
      | address1 | pi@staker.com |
      | address2 | pi@staker.com |
      | subject  | <subject>     |
      | message  | <message>     |
    Then the submit button is disabled
    Examples:
      | name         | subject                                  | message                                                                          |
      | Donald Duck! | This is a test message, please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck  | This is a test message? please delete it | If you don't delete it, it will just clutter up you inbox and you won't be happy |
      | Donald Duck  | This is a test message, please delete it | If you don* delete it, it will just clutter up you inbox and you won't be happy  |

  Scenario Outline: Provide too-short message
  These should be rejected when the send button is clicked
    Given I navigate to the page "contact"
    When I enter the following data
      | name     | Duckal Dond        |
      | address1 | ducky@ducksrus.com |
      | address2 | ducky@ducksrus.com |
      | subject  | Now is the time    |
      | message  | <message>          |
    Then the submit button is disabled
    Examples:
      | message   |
      | woof woof |
      | alphabets |

# Rule: There is a char-counter attached to the message field
  Scenario: Watch the message field's character counter
  Slightly distasteful layout of the when/then, but you can see what is going on
    When I navigate to the page "contact"
    Then the message counter field contains "5000"
    When I enter a message 30 chars long
    Then the message counter field contains "4,970"
    When I enter a message 1 chars long
    Then the message counter field contains "4,969"
    When I enter a message 4000 chars long
    Then the message counter field contains "969"
    When I enter a message 969 chars long
    Then the message counter field contains "0"
    And the message field contains 5000 characters

# Rule: The contact page may have its subject pre-filled
  Scenario Outline: Pre-set the contents of the subject field
  Check also, that the processing of the of the parameter does not break the drawing of the form in some other way
    When I navigate to the page "contact?subject=<testSubject>"
    And the page is fully drawn
    Then the subject field contains "<expectedSubjectContents>"
    Examples:
      | testSubject     | expectedSubjectContents |
      | whos your daddy | whos your daddy         |

  Scenario Outline: Try to break the form by entering malformed parameters
    When I navigate to the page "contact?<testSubject>"
    And the page is fully drawn
    Then the subject field contains "<expectedSubjectContents>"
    And the message field contains 0 characters
    Examples:
      | testSubject                             | expectedSubjectContents |
      | subjict=whos your daddy                 |                         |
      | subject=arfle farfle pippik&disease=flu | arfle farfle pippik     |

# Rule: illegal chars can not be smuggled in to the subject via the pre-fill
  Scenario Outline: Try to put too many chars in the subject via the pre-set of subject
    When I navigate to the page "contact?subject=<testSubject>"
    And the page is fully drawn
    Then the subject field contains "<expectedSubjectContents>"
    Examples:
      | testSubject                                                 | expectedSubjectContents                           |
      | arfle farfle pippik arfle farfle pippik arfle farfle pippik | arfle farfle pippik arfle farfle pippik arfle far |

# rely on the proof that illegal subject field contents are not sent
