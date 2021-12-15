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
    And I take screenshot: Titulo.text

  @test
  Scenario: Handle Dropdown
    Given I am in App main site
    Then I load the DOM Information spotify_registro.json
    And I set text Febrero in dropdown Mes de Nacimiento
    And I set index 03 in dropdown Mes de Nacimiento

  @test
  Scenario: Get Url
    Given I go to site https://www.spotify.com/ar/signup/
    Then I load the DOM Information spotify_registro.json
    And I do a click in element Email
    And I set Email with text kmitnick@mitnicksecurity.com
    And I do a click in element Male
    And I do a click in element Female
    And I do a click in element No binary
    Then I close the window

  @Checkboxes
  Scenario: Handle Dropdown
    Given I am in App main site
    Then I load the DOM Information spotify_registro.json
    And I check the checkbox having Male
    And I check the checkbox having Female
    And I check the checkbox having No binary
    Then I close the window

  # Para chequear el estado del elemento
  @test
  Scenario: I check state
    Given I go to site https://www.spotify.com/ar/signup/
    Then I load the DOM Information spotify_registro.json
    And I set Email with text kmitnick@mitnicksecurity.com
    Then I check if Email Error error message is false
    Then I close the window

  @test
  Scenario: I check if mail is registered
    Given I go to site https://www.spotify.com/ar/signup/
    Then I load the DOM Information spotify_registro.json
    And I set Email with text mervindiazlugo@gmail.com
    And I do a click in element Confirmation Email
    Then Assert if Email Error contains text Este correo electrónico ya está conectado a una cuenta.
    Then Assert if Email Error is equal to Este correo electrónico ya está conectado a una cuenta. Inicia sesión.
    Then I close the window

  ##  Tests from Chercher.tech
  # Para manipular frames
  @frames
  Scenario: Handle various functions
    Given I go to site https://chercher.tech/practice/frames-example-selenium-webdriver
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame2
    And I set text Avatar in dropdown Frame2 select
    And I switch to parent frame
    And I switch to Frame: Frame1
    And I set Frame1 input with text Esto es una prueba
    Then I switch to Frame: Frame3
    And I check the checkbox having Frame3 input

  ##  Tests using Amazon
  #   Para ejecutar scripts de JS
  @test
  Scenario: Click to element with JS functions
    Given I go to site https://www.amazon.es/
    Then I load the DOM Information amazon.json
    And I click in JS element Mi cuenta
    And I wait for element Mis Pedidos to be present

  @test
  Scenario: Scroll to elements
    Given I go to site https://www.amazon.es/
    Then I load the DOM Information amazon.json
    And I scroll to element Sobre Amazon
    And I wait for element Sobre Amazon to be present

  @test
  Scenario: Handle Alerts
    Given I go to site https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert
    Then I load the DOM Information frames.json
    And I switch to Frame: Frame4 Alert
    And I do a click in element Alert
    And I wait 5 seconds
    Then I accept alert

  @Screenshot
  Scenario: take a screenshot
    Given I am in App main site
    And I wait site is loaded
    And I take screenshot: HolyScreen

  @Screenshot
  Scenario: take a screenshot
    Given I am in App main site
    And I wait site is loaded
    And I take screenshot: HolyScreen
    And I attach a Screenshot to Report: Para Allure


