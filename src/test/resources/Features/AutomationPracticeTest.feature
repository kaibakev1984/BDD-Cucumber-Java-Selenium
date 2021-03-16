Feature: Automation Practice

  Background:
    Given I set UserEmail value in Data Scenario

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

  @test
  Scenario Outline: Click in Women block title
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I click in JS element <category>
    Then Assert if Category Name contains text <expected value>

    Examples:
      | category                  | expected value  |
      | T-shirts Category         | T-SHIRTS        |
      | Blouses Category          | BLOUSES         |
      | Casual Dresses Category   | CASUAL DRESSES  |
      | Evening Dresses Category  | EVENING DRESSES |
      | Summer Dresses Category   | SUMMER DRESSES  |

