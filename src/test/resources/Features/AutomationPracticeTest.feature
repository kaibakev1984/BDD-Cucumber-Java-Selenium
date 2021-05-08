Feature: Automation Practice
  Background:
    Given I set UserEmail value in Data Scenario

  @test
  Scenario: Search Query
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
    And I set Email address with text kmitnick@mitnicksecurity.com
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
    And I set Email address with text kmitnick@mitnicksecurity.com
    And I do a click in element Send
    Then Assert if Alert Danger is equal to The message cannot be blank.

  @test
  Scenario: Send Email without selected subject
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I do a click in element Contact us
    And I set Email address with text kmitnick@mitnicksecurity.com
    And I set Message with text Hello world!
    And I do a click in element Send
    Then Assert if Alert Danger is equal to Please select a subject from the list provided.

  @WomenBlock
  Scenario Outline: Click in Women block title
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I click in JS element <category>
    Then Assert if Category Name contains text <expected value>
    And I take screenshot: Automation Practice

    Examples:
      | category                  | expected value  |
      | T-shirts Category         | T-SHIRTS        |
      | Blouses Category          | BLOUSES         |
      | Casual Dresses Category   | CASUAL DRESSES  |
      | Evening Dresses Category  | EVENING DRESSES |
      | Summer Dresses Category   | SUMMER DRESSES  |

  @AddToCart
  Scenario: Add to cart
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I put Dresses in element Search Query
    And I scroll to element Printed Summer Dress
    And I click in JS element Printed Summer Dress
    And I wait for element Continue shopping to be visible
    And I do a click in element Continue shopping
    And I click in JS element Printed Dress
    And I wait for element Continue shopping to be visible
    And I do a click in element Continue shopping
    And I click in JS element Printed Summer Dress 2
    And I wait for element Proceed to checkout to be visible
    And I do a click in element Proceed to checkout
    And I wait for element Total Price to be visible
    And I scroll to element Total Price
    Then Assert if Total Price is equal to $112.47
    And I attach a Screenshot to Report: Add to cart
    And I wait 8 seconds


