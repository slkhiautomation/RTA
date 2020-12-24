Feature: Login
Background:
  Given Navigate to Application
  Scenario: User Login Successfully
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"