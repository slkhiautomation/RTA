Feature: A description

  Background:
    Given Navigate to Application

  Scenario: Nabigate to Dashboard
    When user enter text "inspector" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"