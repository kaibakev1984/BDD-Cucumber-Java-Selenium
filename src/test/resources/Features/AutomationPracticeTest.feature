Feature: Automation Practice
  @test
  Scenario: Search Dresses
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I put Dresses in element Search Query
    Then Assert if Search Lighter is equal to "DRESSES"
    Then Assert if Search Heading Counter contains text 7 results have been found.

  @test
  Scenario: Send a mail
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I do a click in element Contact us
    And I set text Customer service in dropdown Subject Heading
    And I set Email address with text kvasquez@fi.uba.ar
    And I set Order reference with text 1234
    And I set Message with text Hello world!
    And I do a click in element Send
    Then Assert if Alert Success is equal to Your message has been successfully sent to our team.

  @test
  Scenario: Send Email with Invalid email address
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I do a click in element Contact us
    And I do a click in element Send
    Then Assert if Alert Danger is equal to Invalid email address.
    
  @test
  Scenario: Send Email with blank message
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I do a click in element Contact us
    And I set Email address with text kvasquez@fi.uba.ar
    And I do a click in element Send
    Then Assert if Alert Danger is equal to The message cannot be blank.
    
  @test
  Scenario: Send Email without selected subject
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I do a click in element Contact us
    And I set Email address with text kvasquez@fi.uba.ar
    And I set Message with text Hello world!
    And I do a click in element Send
    Then Assert if Alert Danger is equal to Please select a subject from the list provided.