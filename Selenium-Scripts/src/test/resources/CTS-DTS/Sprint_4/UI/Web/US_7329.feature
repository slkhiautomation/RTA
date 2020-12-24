Feature: User Story: 7329 - Left Menu Configuration-Hub have multiple Options such as "Process", "Master" and "Training" Configuration

  Background:
    Given Navigate to Application

    @ConfigurationHub
  Scenario: As User I want to have Sub Menu for Configuration Hub in Left side Navigation
            so that i can move to the Sub Configuration Screens

    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "processConfiguration" appear on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    Then Assert that "trainingConfiguration" appear on "DashboardPage"