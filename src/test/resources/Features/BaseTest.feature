Feature: Examples
  Optional description of the feature

  Background:
    Given I set UserEmail value in Data Scenario

  @test
  Scenario: Get Sites
    Given I am in App main site
    Then I load the DOM Information spotify_registro.json
    And I save text of Titulo as Scenario Context
    And I set Email with key value Titulo.text

  @test
    Scenario: Handle Dropdown
    Given I am in App main site
    Then I load the DOM Information spotify_registro.json
    And I set text Febrero in dropdown Mes de Nacimiento
    And I set index 03 in dropdown Mes de Nacimiento

  @test
    Scenario: Get Url Google
    Given I go to site http://www.google.com

  @test
  Scenario: Get Url
    Given I go to site https://www.spotify.com/ar/signup/
    Then I load the DOM Information spotify_registro.json
    And I do a click in element Email
    And I set Email with text kvasquez@fi.uba.ar
    Then I close the window

  @test
  Scenario: I check state
    Given I go to site https://www.spotify.com/ar/signup/
    Then I load the DOM Information spotify_registro.json
    And I set Email with text kvasquez@fi.uba.ar
    Then I check if Email Error error message is false
    Then I close the window

  @test
  Scenario: Search Dresses
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I put Dresses in element Search Query

  @test
  Scenario: Search Dresses
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I put Shirt in element Search Query

  @test
  Scenario Outline: Search articles
    Given I go to site http://automationpractice.com/index.php
    Then I load the DOM Information automation_practice.json
    And I put <article> in element Search Query
    Examples:
      | article |
      | Dresses |
      | Shirt   |